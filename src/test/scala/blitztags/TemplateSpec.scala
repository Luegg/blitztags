package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class TemplateSpec extends FlatSpec with ShouldMatchers{
  "A template" should "render HTML" in {
    val t = new Template{
      import Tags._
      
      Html{
        Div{"Hello"}
      }
    }
    
    t.renderHtml should equal("<!DOCTYPE html><html><div>Hello</div></html>")
  }
  
  it should "support common scala expressions" in {
    val t = new Template{
      import Tags._
      
      Html{
        if(true)
          Div{"Hello"}
        else
          Div{"World"}
        
        for(i <- 1 to 2){
          P{i}
        }
      }
    }
    
    t.renderHtml should equal("<!DOCTYPE html><html><div>Hello</div><p>1</p><p>2</p></html>")
  }
  
  it should "support inheritance" in {
    import Tags._
    trait Layout{ self: Template =>
      def contentTitle: String
      def content: Unit
      
      Html{
        Title{contentTitle}
        Div{
          content
        }
      }
    }
    
    val t = new Template with Layout{
      def contentTitle = "Welcome"
      def content = {
        P{"Hi there"}
      }
    }
    
    t.renderHtml should equal("<!DOCTYPE html><html><title>Welcome</title><div><p>Hi there</p></div></html>")
  }
  
  it should "allow element attributes" in {
    val t = new Template{
      import Tags._
      
      Div('class -> "container", 'id -> "main"){
        "Something"
      }
    }
    
    t.renderHtml should equal("""<!DOCTYPE html><div class="container" id="main">Something</div>""")
  }
  
  it should "support functional composition" in {
import Tags._
    
    def greet(name: String)(implicit builder: DOMBuilder) = {
      P { s"Hello $name!" }
    }

    val t = new Template{
      Div{
        greet("world")
        greet("scala")
      }
    }
    
    t.renderHtml should equal("""<!DOCTYPE html><div><p>Hello world!</p><p>Hello scala!</p></div>""")
  }
}