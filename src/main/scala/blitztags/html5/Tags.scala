package blitztags.html5

import blitztags._

object Tags {
  // text nodes
  val T = AddText
  
  // comment nodes
  val / = AddComment

  // root element
  val Html = AddNormalElement("html")

  // document metadata
  val Head = AddNormalElement("head")
  val Title = AddRawTextElement("title")
  val Base = AddVoidElement("base")
  val Link = AddVoidElement("link")
  val Meta = AddVoidElement("meta")
  val Style = AddRawTextElement("style")

  // scripting
  val Script = AddRawTextElement("script")
  val Noscript = AddNormalElement("noscript")
  val Template = AddNormalElement("template")

  // section
  val Body = AddNormalElement("body")
  val Article = AddNormalElement("article")
  val Section = AddNormalElement("section")
  val Nav = AddNormalElement("nav")
  val Aside = AddNormalElement("aside")
  val Header = AddNormalElement("header")
  val Footer = AddNormalElement("footer")
  val Address = AddNormalElement("address")
  val H1 = AddNormalElement("h1")
  val H2 = AddNormalElement("h2")
  val H3 = AddNormalElement("h3")
  val H4 = AddNormalElement("h4")
  val H5 = AddNormalElement("h5")
  val H6 = AddNormalElement("h6")

  // grouping
  val P = AddNormalElement("p")
  val Hr = AddVoidElement("hr")
  val Pre = AddNormalElement("pre")
  val Blockquote = AddNormalElement("blockqoute")
  val Ol = AddNormalElement("ol")
  val Ul = AddNormalElement("ul")
  val Li = AddNormalElement("li")
  val Dl = AddNormalElement("dl")
  val Dt = AddNormalElement("dt")
  val Dd = AddNormalElement("dd")
  val Figure = AddNormalElement("figure")
  val Figcaption = AddNormalElement("figcaption")
  val Div = AddNormalElement("div")
  val Main = AddNormalElement("main")

  // text-level semantics
  val A = AddNormalElement("a")
  val Em = AddNormalElement("em")
  val Strong = AddNormalElement("strong")
  val Small = AddNormalElement("small")
  val S = AddNormalElement("s")
  val Cite = AddNormalElement("cite")
  val Q = AddNormalElement("q")
  val Dfn = AddNormalElement("dfn")
  val Abbr = AddNormalElement("abbr")
  val Data = AddNormalElement("data")
  val Time = AddNormalElement("time")
  val Code = AddNormalElement("code")
  val Var = AddNormalElement("var")
  val Samp = AddNormalElement("samp")
  val Kbd = AddNormalElement("kbd")
  val Sub = AddNormalElement("sub")
  val Sup = AddNormalElement("sup")
  val I = AddNormalElement("i")
  val B = AddNormalElement("b")
  val U = AddNormalElement("u")
  val Mark = AddNormalElement("mark")
  val Ruby = AddNormalElement("ruby")
  val Rt = AddNormalElement("rt")
  val Rp = AddNormalElement("rp")
  val Bdi = AddNormalElement("bdi")
  val Bdo = AddNormalElement("bdo")
  val Span = AddNormalElement("span")
  val Br = AddVoidElement("br")
  val Wbr = AddVoidElement("wbr")

  // edits
  val Ins = AddNormalElement("ins")
  val Del = AddNormalElement("del")

  // embedded content
  val Img = AddVoidElement("img")
  val Iframe = AddNormalElement("iframe")
  val Embed = AddVoidElement("embed")
  val Object = AddNormalElement("object")
  val Param = AddVoidElement("param")
  val Video = AddNormalElement("video")
  val Audio = AddNormalElement("audio")
  val Source = AddVoidElement("source")
  val Track = AddVoidElement("track")
  val Canvas = AddNormalElement("canvas")
  val Map = AddNormalElement("map")
  val Area = AddNormalElement("area")

  // tabular data
  val Table = AddNormalElement("table")
  val Caption = AddNormalElement("caption")
  val Colgroup = AddNormalElement("colgroup")
  val Col = AddVoidElement("col")
  val Tbody = AddNormalElement("tbody")
  val Theadd = AddNormalElement("theadd")
  val Tfoot = AddNormalElement("tfoot")
  val Tr = AddNormalElement("tr")
  val Td = AddNormalElement("td")
  val Th = AddNormalElement("th")

  // forms
  val Form = AddNormalElement("form")
  val Fieldset = AddNormalElement("fieldset")
  val Legend = AddNormalElement("legend")
  val Label = AddNormalElement("label")
  val Input = AddVoidElement("input")
  val Button = AddNormalElement("button")
  val Select = AddNormalElement("select")
  val Datalist = AddNormalElement("datalist")
  val Optgroup = AddNormalElement("optgroup")
  val Option = AddNormalElement("option")
  val Textarea = AddRawTextElement("textarea")
  val Keygen = AddVoidElement("keygen")
  val Output = AddNormalElement("output")
  val Progress = AddNormalElement("progress")
  val Meter = AddNormalElement("meter")

  // interactive elements
  val Details = AddNormalElement("details")
  val Summary = AddNormalElement("summary")
  val Menu = AddNormalElement("menu")
  val Menuitem = AddNormalElement("menuitem")
  val Dialog = AddNormalElement("dialog")
}