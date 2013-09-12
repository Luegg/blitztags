package blitztags.html5

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers

class TemplateSpec extends FlatSpec with ShouldMatchers {
  "a HTML5 template" should "print html with doctype" in {
    case class Page(title: String) extends Template {
      Html {
        Head {
          Title { title }
        }
        Body {
          H1 { title }
          P { "Hello blitztags!" }
        }
      }
    }
    
    Page("Welcome").miniPrint should be ("<!DOCTYPE html>\n"
        + "<html><head><title>Welcome</title></head><body><h1>Welcome</h1><p>Hello blitztags!</p></body></html>")
  }
}