package blitztags.html5

import blitztags._

object Tags {
  val T = new {
    def apply(text: String)(implicit builder: DOMBuilder): Unit = {
      builder.addChild(TextNode(text))
    }
  }

  val Html = NormalElementFactory('html)
  val Title = NormalElementFactory('title)
  val Div = NormalElementFactory('div)
  val P = NormalElementFactory('p)
  val Em = NormalElementFactory('em)
  val Script = RawTextElementFactory('script)
  val Br = VoidElementFactory('br)
}