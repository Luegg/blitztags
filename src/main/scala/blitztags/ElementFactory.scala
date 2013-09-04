package blitztags

import scala.collection.mutable.MutableList

trait ElementFactory {
  type T = ElementFactory
  
  val tag: Symbol

  val attributes: MutableList[AttrNode] = MutableList()

  def args2attrs(args: Seq[(Symbol, String)] = Seq()): Vector[AttrNode] = {
    val attrNodes = args map { arg => AttrNode(arg._1, arg._2) }
    attrNodes.toVector
  }
  
  def apply(first: (Symbol, String), attrs: (Symbol, String)*): this.type = {
    attributes ++= args2attrs(first +: attrs)
    this
  }
}

trait Void { self: ElementFactory =>
  def apply()(implicit builder: DOMBuilder): Unit = {
    builder.addChild(VoidElementNode(tag, attributes.toVector))
  }
}

trait Subtree { self: ElementFactory =>
  def apply(expr: => Any)(implicit builder: DOMBuilder): Unit = {
    builder.openElement(ElementNode(tag, attributes.toVector));

    expr match {
      case s: String => builder.addChild(TextNode(s))
      case u: Unit => ()
      case a: Any => builder.addChild(TextNode(a.toString))
    }

    builder.closeElement
  }
}

trait RawText { self: ElementFactory =>
  def apply(text: String)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(ElementNode(tag, attributes.toVector, Vector(TextNode(text))))
  }
}

class VoidElementFactory(val tag: Symbol) extends ElementFactory with Void

class NormalElementFactory(val tag: Symbol) extends ElementFactory with Subtree

class RawTextElementFactory(val tag: Symbol) extends ElementFactory with Void with RawText