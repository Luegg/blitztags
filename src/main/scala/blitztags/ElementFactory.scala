package blitztags

import scala.collection.mutable.MutableList

trait ElementFactory {
  val tag: Symbol

  def args2attrs(args: Seq[(Symbol, Any)] = Seq()): Vector[AttrNode] = {
    val attrNodes = args map { arg => AttrNode(arg._1, arg._2.toString) }
    attrNodes.toVector
  }
}

class VoidElementFactory(val tag: Symbol) extends ElementFactory {
  def apply(attrs: (Symbol, String)*)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(VoidElementNode(tag, args2attrs(attrs)))
  }
}

class NormalElementFactory(val tag: Symbol) extends ElementFactory {
  def apply(attrs: (Symbol, Any)*)(expr: => Any)(implicit builder: DOMBuilder): Unit = {
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

class RawTextElementFactory(val tag: Symbol) extends ElementFactory {
  def apply(attrs: (Symbol, Any)*)(expr: => String = "")(implicit builder: DOMBuilder): Unit = {
    builder.addChild(ElementNode(tag, args2attrs(attrs), Vector(TextNode(expr))))
  }

  def apply(text: String)(implicit builder: DOMBuilder): Unit = {
    apply()(text)(builder)
  }
}