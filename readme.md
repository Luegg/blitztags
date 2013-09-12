# blitztags

[![Build Status](https://travis-ci.org/Luegg/blitztags.png?branch=master)](https://travis-ci.org/Luegg/blitztags)

blitztags is an internal Scala DSL for writing HTML (and XML) templates. It lets you use all the Scala language constructs you are familiar with and does not introduce a new language.

A blitztags template is written in pure Scala:

```scala
import blitztags.html5._

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

This library is distributed under the [MIT](http://en.wikipedia.org/wiki/MIT_License) license.

## Idea

blitztags is inspired by [Play! templates](http://www.playframework.com/documentation/2.1.x/ScalaTemplates) which also reuses the Scala language constructs. Unlike Play! templates, blitztags does not require any additional parsing and is fully supported in every Scala IDE.

A project which has similar goals as blitztags is [ScalaTags](https://github.com/lihaoyi/scalatags). The main difference to blitztags is that ScalaTags does not depend on side effects while building the XML structure. Therefore it strictly follows the functional principles but has a slightly less convenient syntax.

## The Template Trait

Every template must extend the `blitztags.Template` trait. It provides an implicit instance of `XmlBuilder` and the methods `prettyPrint` and `miniPrint` for rendering the HTML/XML.

In order to write a HTML5 template, you can extend `blitztags.html5.Template`.

```scala
import blitztags.html5._

case class Page(title: String) extends Template {
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

The first letter of pre-defined tags is capitalised in order to prevent name collisions with variables and Scala keywords (e.g. the `var` tag).

The following style is used to define HTML attributes:

```scala
Html('lang -> "de"){
  // ...
}
```

Furthermore, every tag method defined in `blitztags.html5.Tags` constructs elements of one of the following types:

- **Void Elements** don't have any content, are always printed without the end tag

        Br() // -> <br/>
        Br('class -> "separator") // -> <br class="separator"/>

- **Raw Text Elements** have only text content, are always printed with end tags

        Title{ "Hello world" } // -> <title>Hello world</title>
        Title('lang -> "en"){} // -> <title lang="en"></title>

- **Normal Elements** Have any kind of content, are always printed with end tags

        Div{ Div{ "text" } } // -> <div><div>text</div></div>
        Div('class -> "empty"){} // -> <div class="empty"></div>

## Examples

Please check the examples in [this specification](https://github.com/Luegg/blitztags/blob/master/src/test/scala/blitztags/examples/Examples.scala).

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

## FAQ

### Is it thread-safe?

Indeed blitztags uses side effects for constructing XML trees, but they are encapsulated and only used during initialisation of `Template` objects. After templates are initialised, you could happily pass them around between threads as you would do with any other value object. 

### Is it fast?

It depends on your point of view:

* **Runtime Performance** Since blitztags constructs a XML tree before generating the output it is probably not very performant compared to template engines that solely depend on string concatenation at runtime.
* **Compile Time Performance** blitztags templates are compiled with scalac, so probably not that fast.
* **Development Performance** I believe this is where blitztags really shines. It has a compact syntax, does not force you to look up some obscure language constructs and makes the use of functions as natural as it should be. It really helps you, to create [DRY](http://en.wikipedia.org/wiki/Don%27t_repeat_yourself) templates that are easy to maintain.