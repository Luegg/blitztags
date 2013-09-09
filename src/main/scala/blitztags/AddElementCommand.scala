package blitztags

import scala.xml._

trait AddElementCommand {
  def args2attrs(args: Seq[(Symbol, Any)]) =
    args.foldRight(Null: MetaData)({ case ((key, value), attr) => new UnprefixedAttribute(key.name, value.toString, attr) })
}

case class AddVoidElement(tagName: String) extends AddElementCommand {
  def apply(attrs: (Symbol, Any)*)(implicit builder: XmlBuilder): Unit = {
    builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, true))
  }
}

case class AddRawTextElement(tagName: String) extends AddElementCommand {
  def apply(attrs: (Symbol, Any)*)(expr: => Any)(implicit builder: XmlBuilder): Unit = {
    val childs = expr match{
      case _: Unit => Seq()
      case a: Any => Seq(Unparsed(a.toString))
    }
    builder.addChild(new Elem(null, tagName, args2attrs(attrs), TopScope, false, childs: _*))
  }

  def apply(expr: => Any)(implicit builder: XmlBuilder): Unit = {
    apply()(expr)(builder)
  }
}

case class AddNormalElement(tagName: String) extends AddElementCommand{
  def apply(attrs: (Symbol, Any)*)(expr: => Any)(implicit builder: XmlBuilder): Unit = {
    builder.startElement(new Elem(null, tagName, args2attrs(attrs), TopScope, true))
    expr match{
      case _: Unit => ()
      case elem: Node => builder.addChild(elem)
      case a: Any => AddText(a.toString)
    }
    builder.endElement
  }
  
  def apply(expr: => Any)(implicit builder: XmlBuilder): Unit = {
    apply()(expr)(builder)
  }
}

object AddComment{
  def apply(text: String)(implicit builder: XmlBuilder): Unit = {
    builder.addChild(new Comment(text))
  }
}

object AddText{
  def apply(text: String)(implicit builder: XmlBuilder): Unit = {
    builder.addChild(new Text(text))
  }
}