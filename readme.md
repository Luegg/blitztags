# blitztags

blitztags is an internal Scala DSL for writing HTML (and XML) templates. It lets you use all the Scala language constructs you are familiar with and does not introduce a new language to achieve the same.

A blitztags template looks like the following:

```scala
case class Page(title: String, items: List[String]) extends Template{
  Html{
    Head{
      Title{ title }
      Script('src -> "main.js"){}
      Script{
        s"console.log($title);"
      }
    }
    
    Body{
      H1{ title }
      P('class -> "intro"){
        "This is some text"
      }
      Ul{
        for(item <- items){
          Li{ item }
        }
      }
    }
  }
}
```

## Idea

blitztags is inspired by [Play! templates](http://www.playframework.com/documentation/2.1.x/ScalaTemplates) which also reuses the Scala language constructs. Unlike Play! templates, blitztags does not require any additional parsing and is fully supported in every Scala IDE.

A project which has similar goals as blitztags is [ScalaTags](https://github.com/lihaoyi/scalatags). The main difference to blitztags is that ScalaTags does not depend on side effects while building the XML structure. Therefore it strictly follows the functional principles but is not as flexible in its syntax.

## The Template Trait

Every template must extend the `blitztags.Template` trait. It provides an implicit instance of `XmlBuilder` and the methods `prettyPrint` and `miniPrint` for rendering the HTML.

In order to write a HTML5 template, you could extend `blitztags.html5.Template`. It contains all HTML5 tags according to the [W3 HTML5 specification](http://www.w3.org/html/wg/drafts/html/master/) and prepends the correct doctype to the output.

```scala
case class Page(title: String) extends blitztags.html5.Template {
  import tags._

  Html{
    Head{
      Title{ title }
    }
  }
}

println(Page("Hello World").prettyPrint)
```

Prints the following HTML document.

```html
<!DOCTYPE html>
<html>
    <head>
        <title>Hello World</title>
    </head>
</html>
```

To access the XML structure of the document just call `Page("Hello World").toXml`.

## Syntax

The first letter of pre-defined tags is capitalised in order to prevent name collisions with variables and scala keywords (e.g. the `var` tag).

The following style is used to define HTML attributes:

```scala
Html('lang -> "de"){
  // ...
}
```

Furthermore, every tag method defined in `blitztags.html5.Tags` constructs elements of one of the following types:

- **Void Elements** Don't have any content, will be parsed without the end tag
  ```scala
  Br() // -> <br/>
  Br('class -> "separator") // -> <br class="separator"/>
  ```
- **Raw Text Elements** Have only text content, blitztags wont escape the content
  ```scala
  Script{ """console.log("<html/>");""" } // -> <script>console.log("<html/>");</script>
  Script('src -> "main.js"){} // -> <script src="main.js"></script>
  ```
- **Normal Elements** Have any kind of content, blitztags escpaes the content
  ```scala
  Div{ "<html/>" } // -> <div>&lt;html/&gt;</div>
  Div('class -> "empty"){} // -> <div class="empty"/>
  ```

## Examples

Please check the examples in [this specification]().

## blitztags in Play!

To use blitztags templates in the Play! framework, Play! needs some help to convert it into a Writeable. Just use a trait like the following in your controllers to provide the required implicits:

```scala
trait WithBlitztags {
  implicit def contentTypeOfBlitztags(implicit codec: Codec): ContentTypeOf[Template] = {
    ContentTypeOf[Template](Some(ContentTypes.HTML))
  }
      
  implicit def writeableOfBlitztags(implicit codec: Codec): Writeable[Template] = {
    Writeable(template => codec.encode(template.prettyPrint))
  }
}
```

Now you're ready to use blitztags:

```scala
// in your views package
case class Index(title: String, message: String) extends Template{
  Html{
    Title{ title }
    Div{
      P { message }
    }
  }
}

// in your controllers package
object Application extends Controller with WithBlitztags {
  def index = Action {
    Ok(views.Index("Hello world", "This site is rendered with blitztags!"))
  }
}
```