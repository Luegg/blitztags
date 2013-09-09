package blitztags

trait Template {
  implicit val builder = new XmlBuilder{}
  
  def document = builder.document.docElem
}