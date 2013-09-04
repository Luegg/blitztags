package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import blitztags.html5._
import Tags._
import scala.collection.immutable.Range

class TemplateSpec extends FlatSpec with ShouldMatchers {
  "A template" should "render HTML" in {
    case class T(msg: String) extends Template{
      Html{
        Div { msg }
        Br()
      }
    }

    T("Hello").renderHtml should equal("<!DOCTYPE html><html><div>Hello</div><br></html>")
  }

  it should "support common scala expressions" in {
    case class T(cond: Boolean, range: Range) extends Template {
      Html {
        if (cond)
          Div { "Hello" }
        else
          Div { "World" }

        for (i <- range) {
          P { i }
        }
      }
    }

    T(true, 1 to 2).renderHtml should equal("<!DOCTYPE html><html><div>Hello</div><p>1</p><p>2</p></html>")
  }

  it should "support inheritance" in {
    trait Layout { self: Template =>
      def contentTitle: String
      def content: Unit

      Html {
        Title { contentTitle }
        Div {
          content
        }
      }
    }

    case class T() extends Template with Layout {
      def contentTitle = "Welcome"
      def content = {
        P { "Hi there" }
      }
    }

    T().renderHtml should equal("<!DOCTYPE html><html><title>Welcome</title><div><p>Hi there</p></div></html>")
  }

  it should "allow element attributes" in {
    case class T(id: String) extends Template {
      Div('class -> "container", 'id -> id) {
        "Something"
      }
    }

    T("main").renderHtml should equal("""<!DOCTYPE html><div class="container" id="main">Something</div>""")
  }

  it should "support attribute methods" in {
    case class T() extends Template {
      Div.id("main").cls("container"){
        "Something"
      }
    }

    T().renderHtml should equal("""<!DOCTYPE html><div id="main" class="container">Something</div>""")
  }

  it should "support methods" in {
    import Tags._

    case class T(times: Int) extends Template {
      def greet(name: String) = {
        P { s"Hello $name!" }
      }

      Div {
        for (i <- 1 to times) {
          greet(i.toString)
        }
      }
    }

    T(3).renderHtml should equal("""<!DOCTYPE html><div><p>Hello 1!</p><p>Hello 2!</p><p>Hello 3!</p></div>""")
  }
}