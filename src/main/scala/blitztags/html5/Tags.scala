package blitztags.html5

import blitztags._

object Tags {
  // a text node factory
  val T = new {
    def apply(text: String)(implicit builder: DOMBuilder): Unit = {
      builder.addChild(TextNode(text))
    }
  }

  // root element
  val Html = new NormalElementFactory('html)

  // document metadata
  val Head = new NormalElementFactory('head)
  val Title = new RawTextElementFactory('title)
  val Base = new VoidElementFactory('base)
  val Link = new VoidElementFactory('link)
  val Meta = new VoidElementFactory('meta)
  val Style = new RawTextElementFactory('style)

  // scripting
  val Script = new RawTextElementFactory('script)
  val Noscript = new NormalElementFactory('noscript)
  val Template = new NormalElementFactory('template)

  // section
  val Body = new NormalElementFactory('body)
  val Article = new NormalElementFactory('article)
  val Section = new NormalElementFactory('section)
  val Nav = new NormalElementFactory('nav)
  val Aside = new NormalElementFactory('aside)
  val Header = new NormalElementFactory('header)
  val Footer = new NormalElementFactory('footer)
  val Address = new NormalElementFactory('address)
  val H1 = new NormalElementFactory('h1)
  val H2 = new NormalElementFactory('h2)
  val H3 = new NormalElementFactory('h3)
  val H4 = new NormalElementFactory('h4)
  val H5 = new NormalElementFactory('h5)
  val H6 = new NormalElementFactory('h6)

  // grouping
  val P = new NormalElementFactory('p)
  val Hr = new VoidElementFactory('hr)
  val Pre = new NormalElementFactory('pre)
  val Blockquote = new NormalElementFactory('blockqoute)
  val Ol = new NormalElementFactory('ol)
  val Ul = new NormalElementFactory('ul)
  val Li = new NormalElementFactory('li)
  val Dl = new NormalElementFactory('dl)
  val Dt = new NormalElementFactory('dt)
  val Dd = new NormalElementFactory('dd)
  val Figure = new NormalElementFactory('figure)
  val Figcaption = new NormalElementFactory('figcaption)
  val Div = new NormalElementFactory('div)
  val Main = new NormalElementFactory('main)

  // text-level semantics
  val A = new NormalElementFactory('a)
  val Em = new NormalElementFactory('em)
  val Strong = new NormalElementFactory('strong)
  val Small = new NormalElementFactory('small)
  val S = new NormalElementFactory('s)
  val Cite = new NormalElementFactory('cite)
  val Q = new NormalElementFactory('q)
  val Dfn = new NormalElementFactory('dfn)
  val Abbr = new NormalElementFactory('abbr)
  val Data = new NormalElementFactory('data)
  val Time = new NormalElementFactory('time)
  val Code = new NormalElementFactory('code)
  val Var = new NormalElementFactory('var)
  val Samp = new NormalElementFactory('samp)
  val Kbd = new NormalElementFactory('kbd)
  val Sub = new NormalElementFactory('sub)
  val Sup = new NormalElementFactory('sup)
  val I = new NormalElementFactory('i)
  val B = new NormalElementFactory('b)
  val U = new NormalElementFactory('u)
  val Mark = new NormalElementFactory('mark)
  val Ruby = new NormalElementFactory('ruby)
  val Rt = new NormalElementFactory('rt)
  val Rp = new NormalElementFactory('rp)
  val Bdi = new NormalElementFactory('bdi)
  val Bdo = new NormalElementFactory('bdo)
  val Span = new NormalElementFactory('span)
  val Br = new VoidElementFactory('br)
  val Wbr = new VoidElementFactory('wbr)

  // edits
  val Ins = new NormalElementFactory('ins)
  val Del = new NormalElementFactory('del)

  // embedded content
  val Img = new VoidElementFactory('img)
  val Iframe = new NormalElementFactory('iframe)
  val Embed = new VoidElementFactory('embed)
  val Object = new NormalElementFactory('object)
  val Param = new VoidElementFactory('param)
  val Video = new NormalElementFactory('video)
  val Audio = new NormalElementFactory('audio)
  val Source = new VoidElementFactory('source)
  val Track = new VoidElementFactory('track)
  val Canvas = new NormalElementFactory('canvas)
  val Map = new NormalElementFactory('map)
  val Area = new NormalElementFactory('area)

  // tabular data
  val Table = new NormalElementFactory('table)
  val Caption = new NormalElementFactory('caption)
  val Colgroup = new NormalElementFactory('colgroup)
  val Col = new VoidElementFactory('col)
  val Tbody = new NormalElementFactory('tbody)
  val Theadd = new NormalElementFactory('theadd)
  val Tfoot = new NormalElementFactory('tfoot)
  val Tr = new NormalElementFactory('tr)
  val Td = new NormalElementFactory('td)
  val Th = new NormalElementFactory('th)

  // forms
  val Form = new NormalElementFactory('form)
  val Fieldset = new NormalElementFactory('fieldset)
  val Legend = new NormalElementFactory('legend)
  val Label = new NormalElementFactory('label)
  val Input = new VoidElementFactory('input)
  val Button = new NormalElementFactory('button)
  val Select = new NormalElementFactory('select)
  val Datalist = new NormalElementFactory('datalist)
  val Optgroup = new NormalElementFactory('optgroup)
  val Option = new NormalElementFactory('option)
  val Textarea = new RawTextElementFactory('textarea)
  val Keygen = new VoidElementFactory('keygen)
  val Output = new NormalElementFactory('output)
  val Progress = new NormalElementFactory('progress)
  val Meter = new NormalElementFactory('meter)

  // interactive elements
  val Details = new NormalElementFactory('details)
  val Summary = new NormalElementFactory('summary)
  val Menu = new NormalElementFactory('menu)
  val Menuitem = new NormalElementFactory('menuitem)
  val Dialog = new NormalElementFactory('dialog)
}