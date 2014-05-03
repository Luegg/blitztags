package blitztags

import blitztags.AddElementCommands._
import scala.xml.Unparsed

trait DefaultNodes {
  // text nodes
  val T = TextNode
  
  // comment nodes
  val / = CommentNode
  
  // raw XML nodes
  val Raw = Unparsed(_)
}