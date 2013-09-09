package blitztags

trait Template {
  implicit val builder = new XmlBuilder{}
  
  def document = builder.document.docElem
  
  def prettyPrint: String
  
  def miniPrint: String
}