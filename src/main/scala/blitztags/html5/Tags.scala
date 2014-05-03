package blitztags.html5

import blitztags.AddElementCommands._

trait Tags {
  // root element
  val Html = NormalElement("html")

  // document metadata
  val Head = NormalElement("head")
  val Title = RawTextElement("title")
  val Base = VoidElement("base")
  val Link = VoidElement("link")
  val Meta = VoidElement("meta")
  val Style = RawTextElement("style")

  // scripting
  val Script = RawTextElement("script")
  val Noscript = NormalElement("noscript")
  val Template = NormalElement("template")

  // section
  val Body = NormalElement("body")
  val Article = NormalElement("article")
  val Section = NormalElement("section")
  val Nav = NormalElement("nav")
  val Aside = NormalElement("aside")
  val Header = NormalElement("header")
  val Footer = NormalElement("footer")
  val Address = NormalElement("address")
  val H1 = NormalElement("h1")
  val H2 = NormalElement("h2")
  val H3 = NormalElement("h3")
  val H4 = NormalElement("h4")
  val H5 = NormalElement("h5")
  val H6 = NormalElement("h6")

  // grouping
  val P = NormalElement("p")
  val Hr = VoidElement("hr")
  val Pre = NormalElement("pre")
  val Blockquote = NormalElement("blockqoute")
  val Ol = NormalElement("ol")
  val Ul = NormalElement("ul")
  val Li = NormalElement("li")
  val Dl = NormalElement("dl")
  val Dt = NormalElement("dt")
  val Dd = NormalElement("dd")
  val Figure = NormalElement("figure")
  val Figcaption = NormalElement("figcaption")
  val Div = NormalElement("div")
  val Main = NormalElement("main")

  // text-level semantics
  val A = NormalElement("a")
  val Em = NormalElement("em")
  val Strong = NormalElement("strong")
  val Small = NormalElement("small")
  val S = NormalElement("s")
  val Cite = NormalElement("cite")
  val Q = NormalElement("q")
  val Dfn = NormalElement("dfn")
  val Abbr = NormalElement("abbr")
  val Data = NormalElement("data")
  val Time = NormalElement("time")
  val Code = NormalElement("code")
  val Var = NormalElement("var")
  val Samp = NormalElement("samp")
  val Kbd = NormalElement("kbd")
  val Sub = NormalElement("sub")
  val Sup = NormalElement("sup")
  val I = NormalElement("i")
  val B = NormalElement("b")
  val U = NormalElement("u")
  val Mark = NormalElement("mark")
  val Ruby = NormalElement("ruby")
  val Rt = NormalElement("rt")
  val Rp = NormalElement("rp")
  val Bdi = NormalElement("bdi")
  val Bdo = NormalElement("bdo")
  val Span = NormalElement("span")
  val Br = VoidElement("br")
  val Wbr = VoidElement("wbr")

  // edits
  val Ins = NormalElement("ins")
  val Del = NormalElement("del")

  // embedded content
  val Img = VoidElement("img")
  val Iframe = NormalElement("iframe")
  val Embed = VoidElement("embed")
  val Object = NormalElement("object")
  val Param = VoidElement("param")
  val Video = NormalElement("video")
  val Audio = NormalElement("audio")
  val Source = VoidElement("source")
  val Track = VoidElement("track")
  val Canvas = NormalElement("canvas")
  val Map = NormalElement("map")
  val Area = NormalElement("area")

  // tabular data
  val Table = NormalElement("table")
  val Caption = NormalElement("caption")
  val Colgroup = NormalElement("colgroup")
  val Col = VoidElement("col")
  val Tbody = NormalElement("tbody")
  val Theadd = NormalElement("theadd")
  val Tfoot = NormalElement("tfoot")
  val Tr = NormalElement("tr")
  val Td = NormalElement("td")
  val Th = NormalElement("th")

  // forms
  val Form = NormalElement("form")
  val Fieldset = NormalElement("fieldset")
  val Legend = NormalElement("legend")
  val Label = NormalElement("label")
  val Input = VoidElement("input")
  val Button = NormalElement("button")
  val Select = NormalElement("select")
  val Datalist = NormalElement("datalist")
  val Optgroup = NormalElement("optgroup")
  val Option = NormalElement("option")
  val Textarea = RawTextElement("textarea")
  val Keygen = VoidElement("keygen")
  val Output = NormalElement("output")
  val Progress = NormalElement("progress")
  val Meter = NormalElement("meter")

  // interactive elements
  val Details = NormalElement("details")
  val Summary = NormalElement("summary")
  val Menu = NormalElement("menu")
  val Menuitem = NormalElement("menuitem")
  val Dialog = NormalElement("dialog")
}