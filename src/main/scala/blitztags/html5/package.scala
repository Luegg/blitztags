package blitztags

import scala.xml.MetaData
import scala.xml.Null
import scala.xml.Text
import scala.xml.TopScope
import scala.xml.UnprefixedAttribute

package object html5 extends Tags with Implicits {
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