package blitztags.html5

import blitztags._

object Attributes {
  // global attributes, may be specified on all HTML elements
  // http://www.w3.org/html/wg/drafts/html/master/dom.html#global-attributes
  trait Global { self: ElementFactory =>
    def accesskey(v: String): this.type = apply('accesskey -> v)
    def cls(v: String): this.type = apply('class -> v)
    def contenteditable(v: String): this.type = apply('contenteditable -> v)
    def contextmenu(v: String): this.type = apply('contextmenu -> v)
    def dir(v: String): this.type = apply('dir -> v)
    def draggable(v: String): this.type = apply('draggable -> v)
    def dropzone(v: String): this.type = apply('dropzone -> v)
    def hidden(v: String): this.type = apply('hidden -> v)
    def id(v: String): this.type = apply('id -> v)
    def inert(v: String): this.type = apply('inert -> v)
    def lang(v: String): this.type = apply('lang -> v)
    def spellcheck(v: String): this.type = apply('spellcheck -> v)
    def style(v: String): this.type = apply('style -> v)
    def tabindex(v: String): this.type = apply('tabindex -> v)
    def title(v: String): this.type = apply('title -> v)
    def translate(v: String): this.type = apply('translate -> v)
  }
}

object Tags {
  import Attributes._

  val T = new {
    def apply(text: String)(implicit builder: DOMBuilder): Unit = {
      builder.addChild(TextNode(text))
    }
  }

  def Html = new NormalElementFactory('html) with Global
  def Head = new NormalElementFactory('html) with Global
  def Body = new NormalElementFactory('html) with Global
  def Title = new NormalElementFactory('title) with Global
  def Div = new NormalElementFactory('div) with Global
  def P = new NormalElementFactory('p) with Global
  def Em = new NormalElementFactory('em) with Global
  def Script = new RawTextElementFactory('script) with Global
  def Br = new VoidElementFactory('br) with Global
  def Form = new NormalElementFactory('form) with Global{
    def method(v: String): this.type = apply('method -> v)
    def action(v: String): this.type = apply('action -> v)
  }
}