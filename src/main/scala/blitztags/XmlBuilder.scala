package blitztags

import scala.xml._

class XmlBuilder {
  trait XmlWrapper {
    def addChild(element: Elem): XmlWrapper
  }

  implicit class ExtDocument(doc: Document) extends XmlWrapper {
    def addChild(element: Elem) = {
      doc.docElem = element
      doc
    }
  }

  implicit class ExtElem(elem: Elem) extends XmlWrapper {
    def addChild(element: Elem) = elem.copy(child = elem.child.toSeq ++ element)
  }

  val document = new Document()

  var currentNode: XmlWrapper = document

  var workStack: List[XmlWrapper] = Nil

  def addChild(element: Elem) = {
    currentNode = currentNode.addChild(element)
  }

  def startElement(element: Elem) = {
    workStack = currentNode :: workStack
    currentNode = element
  }

  def endElement() = {
    val parent = workStack.head.addChild(currentNode match {
      case e: Elem => e
    })
    currentNode = parent
    workStack = workStack.tail
  }
}