package blitztags.html5

import scala.xml.PrettyPrinter

trait Template extends blitztags.Template{
  val doctype = "<!DOCTYPE html>"
    
  override def prettyPrint = {
    doctype + "\n" + prettyPrinter.format(toXml)
  }
    
  override def miniPrint = {
    doctype + "\n" + toXml.toString
  }
}