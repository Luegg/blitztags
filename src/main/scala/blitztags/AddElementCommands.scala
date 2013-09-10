package blitztags

import scala.xml._

object AddElementCommands {
  def args2attrs(args: Seq[(Symbol, Any)]) =
    args.foldRight(Null: MetaData)({ case ((key, value), attr) => new UnprefixedAttribute(key.name, value.toString, attr) })

  case class VoidElement(tagName: String) {
    def apply(attrs: (Symbol, Any)*)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, true))
    }
  }

  case class RawTextElement(tagName: String) {
    def apply(attrs: (Symbol, Any)*)(content: Any)(implicit builder: XmlBuilder): Unit = {
      val childs = content match {
        case _: Unit => Seq()
        case a: Any => Seq(new Text(a.toString))
      }
      builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, false, childs: _*))
    }

    def apply(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      apply()(expr)(builder)
    }
  }

  case class NormalElement(tagName: String) {
    def apply(attrs: (Symbol, Any)*)(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      builder.startElement(new Elem(null, tagName, args2attrs(attrs), TopScope, true))
      expr match {
        case _: Unit => ()
        case elem: Node => builder.addChild(elem)
        case a: Any => TextNode(a.toString)
      }
      builder.endElement
    }

    def apply(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      apply()(expr)(builder)
    }
  }

  object CommentNode {
    def apply(text: String)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Comment(text))
    }
  }

  object TextNode {
    def apply(text: String)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Text(text))
    }
  }
}