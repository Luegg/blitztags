package blitztags

trait Template {
  implicit val builder = new DOMBuilder()
  
  def renderHtml() = {
    builder.currentNode.toString()
  }
}