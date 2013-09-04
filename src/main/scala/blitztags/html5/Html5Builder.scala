package blitztags.html5

import blitztags._

object Html5Doctype extends DocumentTypeNode("<!DOCTYPE html>")

class Html5Builder extends DOMBuilder {
  def rootNode = DocumentNode(Html5Doctype)
}