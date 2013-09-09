package blitztags.html5

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FreeSpec
import Tags._
import scala.xml._
import org.scalatest.matchers.Matcher
import org.scalatest.matchers.MatchResult
import blitztags.AddVoidElement
import blitztags.AddRawTextElement
import blitztags.AddNormalElement
import blitztags.TemplateMatchers

class Examples extends FreeSpec with ShouldMatchers with TemplateMatchers{
  "blitztags examples" - {
    "hello world" in {
      case class Page(title: String) extends Template {
        Html {
          Head {
            Title { title }
          }
        }
      }

      Page("Hello World") should matchXml(
        <html>
          <head>
            <title>Hello World</title>
          </head>
        </html>)
    }

    "controll strcutures and variables" in {
      case class Page(title: String, items: List[Any]) extends Template {
        Html {
          val allCaps = title.toUpperCase
          Title { allCaps }
          Ul {
            for (item <- items) {
              Li {
                item match {
                  case s: String => B { s }
                  case a: Any => a
                }
              }
            }
          }
        }
      }

      Page("Welcome", 1 :: "two" :: 3 :: "four" :: Nil) should matchXml(
        <html>
          <title>WELCOME</title>
          <ul>
            <li>1</li>
            <li><b>two</b></li>
            <li>3</li>
            <li><b>four</b></li>
          </ul>
        </html>)
    }

    "methods" in {
      new Template {
        def twiceAndBold(content: => Any) = {
          B { content }
          B { content }
        }

        Html {
          twiceAndBold { I { "test" } }
          var i = 0
          twiceAndBold { i = i + 1; i }
        }
      } should matchXml(
        <html>
          <b><i>test</i></b><b><i>test</i></b>
          <b>1</b><b>2</b>
        </html>)
    }

    "layouts and inheritance" in {
      trait Layout { self: Template =>
        def page(title: String)(content: => Unit) = {
          Html {
            Title { title }
            Body {
              Div('class -> "container") {
                content
              }
            }
          }
        }
      }

      new Template with Layout {
        page("Fairytale") {
          P { "Once upon a time..." }
        }
      } should matchXml(
        <html>
          <title>Fairytale</title>
          <body>
            <div class="container">
              <p>Once upon a time...</p>
            </div>
          </body>
        </html>)
    }

    "text nodes and html comments" in {
      new Template {
        Html {
          T { "hi" }
          / { "just one more slash for a Scala comment" }
        }
      } should matchXml(
        <html>hi<!--just one more slash for a Scala comment--></html>)
    }

    "inline XML" in {
      new Template {
        Html {
          Div {
            <p><em>Sometimes</em> it's easier to just use <small>XML</small> literals</p>
          }
        }
      } should matchXml(
        <html>
          <div>
            <p><em>Sometimes</em> it's easier to just use <small>XML</small> literals</p>
          </div>
        </html>)
    }

    "unescaped text" in {
      new Template {
        Html {
          Unparsed("""<script>alert("XSS!");</script>""")
        }
      }.toXml.toString should be(
        """<html><script>alert("XSS!");</script></html>""")
    }

    "custom tags" in {
      new Template {
        val Ruler = AddVoidElement("ruler")
        val Story = AddRawTextElement("story")
        val Cloud = AddNormalElement("cloud")

        Html {
          Ruler('from -> 1, 'to -> 8)
          Story { "Once upon a time..." }
          Cloud {
            P { "first" }
            P { "second" }
          }
        }
      } should matchXml(
        <html>
          <ruler from="1" to="8"/>
          <story>Once upon a time...</story>
          <cloud>
            <p>first</p>
            <p>second</p>
          </cloud>
        </html>)
    }
  }
}