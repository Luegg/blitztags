package blitztags

import scala.collection.mutable.MutableList

trait ElementFactory {
  val tag: Symbol

  def args2attrs(args: Seq[(Symbol, String)] = Seq()): Vector[AttrNode] = {
    val attrNodes = args map { arg => AttrNode(arg._1, arg._2) }
    attrNodes.toVector
  }
}

trait Void { self: ElementFactory =>
  def apply(attrs: (Symbol, String)*)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(VoidElementNode(tag, args2attrs(attrs)))
  }
}

trait Subtree { self: ElementFactory =>
  def apply(attrs: (Symbol, String)*)(expr: => Any)(implicit builder: DOMBuilder): Unit = {
    builder.openElement(ElementNode(tag, args2attrs(attrs)));

    expr match {
      case s: String => builder.addChild(TextNode(s))
      case u: Unit => ()
      case a: Any => builder.addChild(TextNode(a.toString))
    }

    builder.closeElement
  }
  
  def apply(expr: => Any)(implicit builder: DOMBuilder): Unit = {
    apply()(expr)(builder)
  }
}

trait RawText { self: ElementFactory =>
  def apply(attrs: (Symbol, String)*)(text: String)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(ElementNode(tag, args2attrs(attrs), Vector(TextNode(text))))
  }
  
  def apply(text: String)(implicit builder: DOMBuilder): Unit = {
    apply()(text)(builder)
  }
}

class VoidElementFactory(val tag: Symbol) extends ElementFactory with Void

class NormalElementFactory(val tag: Symbol) extends ElementFactory with Subtree

class RawTextElementFactory(val tag: Symbol) extends ElementFactory with Void with RawText