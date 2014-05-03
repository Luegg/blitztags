package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import scala.xml._
import blitztags.AddElementCommands._

class VoidElementSpec extends FlatSpec with ShouldMatchers with MockFactory {
  val Br = VoidElement("br")

  "the AddVoidElement command" should "instruct the builder to add a new void element" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<br/>)

    Br()
  }

  it should "add attributes to the void element" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<br class="big" id="first"/>)

    Br('class -> "big", 'id -> "first")
  }
}

class RawTextElementSpec extends FlatSpec with ShouldMatchers with MockFactory {
  val Script = RawTextElement("script")

  "the AddRawTextElement command" should "instruct the builder to add a new raw text element" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<script>console.log("Hello World");</script>)

    Script {
      """console.log("Hello World");"""
    }
  }

  it should "add attributes to the raw text element" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<script src="js/main.js"></script>)

    Script('src -> "js/main.js") {}
  }
}

class NormalElementSpec extends FlatSpec with ShouldMatchers with MockFactory {
  val P = NormalElement("p")
  val H1 = NormalElement("h1")
  val H2 = NormalElement("h2")

  "the AddNormalElement command" should "instruct the builder to add a new normal element" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.addChild _) expects (new Text("Hello world"))
    (b.endElement _) expects ()

    P {
      "Hello world"
    }
  }

  it should "add attributes to the normal element" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p class="container" id="first"/>)
    (b.endElement _) expects ()

    P('class -> "container", 'id -> "first") {}
  }

  it should "add XML objects as child" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.addChild _) expects (<b>the XML literal</b>)
    (b.endElement _) expects ()

    P {
      <b>the XML literal</b>
    }
  }

  it should "execute inner commands in appropriate order" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.startElement _) expects (<h1/>)
    (b.addChild _) expects (new Text("first"))
    (b.endElement _) expects ()
    (b.startElement _) expects (<h2/>)
    (b.addChild _) expects (new Text("second"))
    (b.endElement _) expects ()
    (b.endElement _) expects ()

    P {
      H1 { "first" }
      H2 { "second" }
    }
  }

  it should "allow Unparsed xml nodes" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.addChild _) expects (Unparsed("""<script>console.log("oops");</script>"""))
    (b.endElement _) expects ()

    P {
      Unparsed("""<script>console.log("oops");</script>""")
    }
  }

  it should "executes sequential commands in correct order" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.endElement _) expects ()
    (b.startElement _) expects (<p/>)
    (b.endElement _) expects ()

    P {}
    P {}
  }
}

class CommentNodeSpec extends FlatSpec with ShouldMatchers with MockFactory {
  val Comment = CommentNode

  "the AddComment command" should "instruct the builder to add a new comment" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (new Comment("invisible"))

    Comment { "invisible" }
  }
}

class TextNodeSpec extends FlatSpec with ShouldMatchers with MockFactory {
  val T = TextNode

  "the AddText command" should "instruct the builder to add a new text node" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (new Text("this is text"))

    T { "this is text" }
  }
}