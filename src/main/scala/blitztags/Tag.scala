package blitztags

trait ElementBuilder {
  val tag: Symbol

  def args2attrs(args: Seq[(Symbol, String)] = Seq()): Vector[AttrNode] = {
    val attrNodes = args map { arg => AttrNode(arg._1, arg._2) }
    attrNodes.toVector
  }
}

trait Void { self: ElementBuilder =>
  def apply(attrs: (Symbol, String)*)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(VoidElementNode(tag, args2attrs(attrs)))
  }
}

trait Subtree { self: ElementBuilder =>
  def apply(expr: => Any)(implicit builder: DOMBuilder): Unit = {
    apply()(expr)(builder)
  }

  def apply(attrs: (Symbol, String)*)(expr: => Any)(implicit builder: DOMBuilder): Unit = {
    builder.openElement(ElementNode(tag, args2attrs(attrs)));
    
    expr match {
      case s: String => builder.addChild(TextNode(s))
      case u: Unit => ()
      case a: Any => builder.addChild(TextNode(a.toString))
    }
    
    builder.closeElement
  }
}

trait RawText { self: ElementBuilder =>
  def apply(text: String)(implicit builder: DOMBuilder): Unit = {
    apply(Seq(): _*)(text)(builder)
  }

  def apply(attrs: (Symbol, String)*)(text: String)(implicit builder: DOMBuilder): Unit = {
    builder.addChild(ElementNode(tag, args2attrs(attrs), Vector(TextNode(text))))
  }
}

case class VoidElementBuilder(tag: Symbol) extends ElementBuilder with Void

case class NormalElementBuilder(tag: Symbol) extends ElementBuilder with Subtree

case class RawTextElementBuilder(tag: Symbol) extends ElementBuilder with Void with RawText

object Tags {
  val T = new {
    def apply(text: String)(implicit builder: DOMBuilder): Unit = {
      builder.addChild(TextNode(text))
    }
  }

  val Html = NormalElementBuilder('html)
  val Title = NormalElementBuilder('title)
  val Div = NormalElementBuilder('div)
  val P = NormalElementBuilder('p)
  val Em = NormalElementBuilder('em)
  val Script = RawTextElementBuilder('script)
  val Br = VoidElementBuilder('br)
}