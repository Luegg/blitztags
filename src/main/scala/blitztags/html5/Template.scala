package blitztags.html5

import blitztags.DOMBuilder

trait Template {
  implicit val builder = new Html5Builder()
  
  def renderHtml() = {
    builder.currentNode.toString()
  }
}