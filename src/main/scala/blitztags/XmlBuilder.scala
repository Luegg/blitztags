package blitztags

import scala.xml._

trait XmlBuilder {
  protected trait XmlWrapper {
    def addChild(n: Node): XmlWrapper
  }

  implicit protected class ExtDocument(val doc: Document) extends XmlWrapper {
    def addChild(n: Node) = {
      doc.docElem = n
      doc
    }
  }

  implicit protected class ExtElem(val elem: Elem) extends XmlWrapper {
    def addChild(n: Node) = elem.copy(child = elem.child.toSeq ++ n)
  }

  val document = new Document()

  // explicitly apply implicit class because of issues with scalamock
  protected var currentNode: XmlWrapper = new ExtDocument(document)

  protected var workStack: List[XmlWrapper] = Nil

  def addChild(n: Node): Unit = {
    currentNode = currentNode.addChild(n)
  }

  def startElement(element: Elem): Unit = {
    workStack = currentNode :: workStack
    currentNode = element
  }

  def endElement(): Unit = {
    val parent = workStack.head.addChild(currentNode match {
      case e: ExtElem => e.elem
    })
    currentNode = parent
    workStack = workStack.tail
  }
}