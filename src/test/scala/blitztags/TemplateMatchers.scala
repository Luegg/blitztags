package blitztags

import scala.xml._
import org.scalatest.matchers.Matcher
import org.scalatest.matchers.MatchResult

trait TemplateMatchers {
  val prettyPrinter = new PrettyPrinter(80, 4)

  val matchXml = (right: Node) => new Matcher[Template] {
    def apply(left: Template) = {
      val leftRes = prettyPrinter.format(left.toXml)
      val rightRes = prettyPrinter.format(right)
      MatchResult(
        leftRes == rightRes,
        "Template\n" + leftRes + "\nis not congruent with\n" + rightRes,
        "Template\n" + leftRes + "\nis congruent with\n" + rightRes)
    }
  }
}