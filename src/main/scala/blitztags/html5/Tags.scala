package blitztags.html5

import blitztags._

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