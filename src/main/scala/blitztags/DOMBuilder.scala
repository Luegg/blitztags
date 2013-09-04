package blitztags

class DOMBuilder(){
  var currentNode: InnerNode = Html5Document
  var workStack: List[InnerNode] = Nil
  
  def addChild(n: Node): Unit = {
    currentNode = currentNode.appendChild(n)
  }
  
  def openElement(n: ElementNode): Unit = {
    workStack = currentNode :: workStack
    currentNode = n
  }
  
  def closeElement(): Unit = {
    val parent = workStack.head.appendChild(currentNode)
    currentNode = parent
    workStack = workStack.tail
  }
}