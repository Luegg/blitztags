package blitztags

import scala.xml._

/**
 * Provides classes for creating XML/HTML nodes.
 * 
 * The commands uses the [[scala.xml]] library for creating nodes, therefore the
 * input is always sanitized. If you want to pass unsanitized input, wrap your data
 * in `scala.xml.Unparsed`.
 */
object AddElementCommands {
  trait Attr{
    def prependTo(attributes: MetaData): MetaData
  }
  
  class AttrPair(val p: (Symbol, String)) extends Attr{
    def prependTo(attributes: MetaData) =
      new UnprefixedAttribute(p._1.name, p._2, attributes)
  }
  
  /**
   * Converts HTML attribute arguments to [[scala.xml.Metadata]].
   * 
   * It converts every value to a String by calling it's `toString` method.
   */
  def args2attrs(args: Seq[Attr]) =
    args.foldRight(Null: MetaData)({ case (a, as) => a.prependTo(as) })

  /**
   * A command that creates void HTML elements.
   *
   * Void elements don't have any content, just attributes. They are never printed with the end tag.
   * 
   * {{{
   * implicit val _ = new XmlBuilder
   * val Br = VoidElement("br")
   * 
   * // add <br/> to builder
   * Br()
   * // add <br class="ruler"/> to builder
   * Br('class -> "ruler")
   * }}}
   */
  case class VoidElement(tagName: String) {
    /**
     * Instructs the `builder` to append a void element with tag `tagName` and the attributes `attrs`
     */
    def apply(attrs: Attr*)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, true))
    }
  }

  /**
   * A command that creates raw text HTML elements.
   * 
   * Raw text elements have only text content. They are always printed with the according end tag.
   * 
   * {{{
   * implicit val _ = new XmlBuilder
   * val Script = RawTextElement("script")
   * 
   * // add <script src="main.js"></script>
   * Script('src -> "main.js"){}
   * // add <script>console.log(10);</script>
   * Script{
   *   "console.log(10);"
   * }
   * }}}
   */
  case class RawTextElement(tagName: String) {
    def apply(attrs: Attr*)(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      val childs = expr match {
        case _: Unit => Seq()
        case a: Any => Seq(new Text(a.toString))
      }
      builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, false, childs: _*))
    }

    def apply(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      apply()(expr)(builder)
    }
  }

  /**
   * A command that creates normal HTML elements.
   * 
   * Normal elements content is either empty, text or a sequence of HTML nodes.
   * They are always printed with the according end tag.
   * 
   * {{{
   * implicit val _ = new XmlBuilder
   * val Div = NormalElement("div")
   * 
   * // add <div class="container"/>
   * Div('class -> "container"){}
   * // add <div><div/></div>
   * Div{
   *   Div{}
   * }
   * }}}
   */
  case class NormalElement(tagName: String) {
    def apply(attrs: Attr*)(expr: => Any)(implicit builder: XmlBuilder): Unit = {
      builder.startElement(new Elem(null, tagName, args2attrs(attrs), TopScope, false))
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

  /**
   * Creates HTML comment nodes.
   */
  object CommentNode {
    def apply(text: String)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Comment(text))
    }
  }

  /**
   * Creates HTML text nodes.
   */
  object TextNode {
    def apply(text: String)(implicit builder: XmlBuilder): Unit = {
      builder.addChild(new Text(text))
    }
  }
}