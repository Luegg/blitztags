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
      case class Page(text: String) extends Template {
        def twiceAndBold(content: => Any) = {
          B { content }
          B { content }
        }

        Html {
          twiceAndBold { I { text } }
          var i = 0
          twiceAndBold { i = i + 1; i }
        }
      }

      Page("test") should matchXml(
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

      case class Page(title: String) extends Template with Layout {
        page(title) {
          P { "Once upon a time..." }
        }
      }

      Page("Fairytale") should matchXml(
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
      case class Page(text: String) extends Template {
        Html {
          T { text }
          / { "just one more slash for a Scala comment" }
        }
      }

      Page("hi") should matchXml(
        <html>hi<!--just one more slash for a Scala comment--></html>)
    }

    "inline XML" in {
      case class Page() extends Template {
        Html {
          Div {
            <p><em>Sometimes</em> it's easier to just use <small>XML</small> literals</p>
          }
        }
      }

      Page() should matchXml(
        <html>
          <div>
            <p><em>Sometimes</em> it's easier to just use <small>XML</small> literals</p>
          </div>
        </html>)
    }

    "unescaped text" in {
      case class Page(harmless: String) extends Template {
        Html {
          Unparsed(harmless)
        }
      }

      Page("""<script>alert("XSS!");</script>""").toXml.toString should be(
        """<html><script>alert("XSS!");</script></html>""")
    }

    "custom tags" in {
      new Template {
        val Ruler = AddVoidElement("ruler")
        val Story = AddRawTextElement("story")
        val Cloud = AddNormalElement("cloud")

        Html {
          Ruler()
          Story { "Once upon a time..." }
          Cloud {
            P { "first" }
            P { "second" }
          }
        }
      } should matchXml(
        <html>
          <ruler/>
          <story>Once upon a time...</story>
          <cloud>
            <p>first</p>
            <p>second</p>
          </cloud>
        </html>)
    }
  }
}