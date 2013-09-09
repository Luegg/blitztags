package blitztags

import scala.xml.PrettyPrinter

trait Template {
  implicit val builder = new XmlBuilder{}
  
  def toXml = builder.document.docElem
  
  val prettyPrinter = new PrettyPrinter(80, 4)
  
  def prettyPrint = prettyPrinter.format(toXml)
  
  def miniPrint = toXml.toString
}