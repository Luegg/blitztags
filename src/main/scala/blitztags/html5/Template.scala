package blitztags.html5

import scala.xml.PrettyPrinter

trait Template extends blitztags.Template{
  val tags = blitztags.html5.Tags
  
  val doctype = "<!DOCTYPE html>"
    
  val prettyPrinter = new PrettyPrinter(80, 4)
    
  def prettyPrint = {
    doctype + "\n" + prettyPrinter.format(toXml)
  }
    
  def miniPrint = {
    doctype + "\n" + toXml.toString
  }
}