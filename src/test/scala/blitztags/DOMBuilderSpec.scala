package blitztags

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

import blitztags.DOMBuilder;
import blitztags.ElementNode;
import blitztags.TextNode;

class DOMBuilderSpec extends FlatSpec with ShouldMatchers{
  
  val rootNode = new ElementNode('html)
  val aliceNode = new TextNode("Hello Alice")
  val bobNode = new TextNode("Hello Bob")
  
  "A DOMBuilder" should "add child nodes to a node" in {
    var builder = new html5.Html5Builder
    
    builder.addChild(aliceNode)
    builder.addChild(bobNode)
    
    println(builder.currentNode)
    
    builder.currentNode.childNodes should equal (Vector(aliceNode, bobNode))
  }
  
  it should "open and close nodes" in {
    var builder = new html5.Html5Builder
    
    builder.openElement(new ElementNode('div))
    
    builder.addChild(aliceNode)
    builder.addChild(bobNode)
    
    builder.closeElement
    
    println(builder.currentNode.getClass)
    
    builder.currentNode.childNodes should equal ()
  }
}