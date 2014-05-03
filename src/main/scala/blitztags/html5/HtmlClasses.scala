package blitztags.html5

import scala.xml._
import blitztags.AddElementCommands

trait HtmlClasses {
  case class Clazz(name: String) extends AddElementCommands.Attr {
    def prependTo(attrs: MetaData) = {
      val classAttr = attrs.get("class") match {
        case Some(as) =>
          val vs = Text(name + " ") +: as
          new UnprefixedAttribute("class", Some(vs), Null)
        case None =>
          new UnprefixedAttribute("class", name, Null)
      }
      attrs.append(classAttr)
    }
  }
}