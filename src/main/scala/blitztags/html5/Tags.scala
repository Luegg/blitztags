package blitztags.html5

import blitztags._

object Tags {
  val T = new {
    def apply(text: String)(implicit builder: DOMBuilder): Unit = {
      builder.addChild(TextNode(text))
    }
  }

  val Html = new NormalElementFactory('html)
  val Head = new NormalElementFactory('head)
  val Body = new NormalElementFactory('body)
  val Title = new NormalElementFactory('title)
  val Div = new NormalElementFactory('div)
  val Span = new NormalElementFactory('span)
  val P = new NormalElementFactory('p)
  val Em = new NormalElementFactory('em)
  val H1 = new NormalElementFactory('h1)
  val H2 = new NormalElementFactory('h2)
  val Script = new RawTextElementFactory('script)
  val Br = new VoidElementFactory('br)
  val Form = new NormalElementFactory('form)
  val Label = new NormalElementFactory('label)
  val Input = new NormalElementFactory('input)
  val Link = new NormalElementFactory('link)
}