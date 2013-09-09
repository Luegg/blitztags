package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalamock.scalatest.MockFactory
import scala.xml.Text
import scala.xml.Unparsed
import scala.xml.Comment

class AddVoidElementSpec extends FlatSpec with ShouldMatchers with MockFactory{
  val Br = AddVoidElement("br")

  "the AddVoidElement command" should "instruct the builder to add a new void element" in{
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<br/>)
    
    Br()
  }
  
  it should "add attributes to the void element" in{
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<br class="big" id="first"/>)
    
    Br('class -> "big", 'id -> "first")
  }
}

class AddRawTextElementSpec extends FlatSpec with ShouldMatchers with MockFactory{
  val Script = AddRawTextElement("script")

  "the AddRawTextElement command" should "instruct the builder to add a new raw text element" in{
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<script>{Unparsed("""console.log("Hello World");""")}</script>)
    
    Script{
      """console.log("Hello World");"""
    }
  }
  
  it should "add attributes to the raw text element" in{
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (<script src="js/main.js"></script>)
    
    Script('src -> "js/main.js"){}
  }
}

class AddNormalElementSpec extends FlatSpec with ShouldMatchers with MockFactory{
  val P = AddNormalElement("p")
  val H1 = AddNormalElement("h1")
  val H2 = AddNormalElement("h2")
  
  "the AddNormalElement command" should "instruct the builder to add a new normal element" in{
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.addChild _) expects (new Text("Hello world"))
    (b.endElement _) expects ()
    
    P{
      "Hello world"
    }
  }
  
  it should "add attributes to the normal element" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p class="container" id="first"/>)
    (b.endElement _) expects ()
    
    P('class -> "container", 'id -> "first"){}
  }
  
  it should "add XML objects as child" in {
    implicit val b = mock[XmlBuilder]
    (b.startElement _) expects (<p/>)
    (b.addChild _) expects (<b>the XML literal</b>)
    (b.endElement _) expects ()
    
    P{
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
    
    P{
      H1{ "first" }
      H2{ "second" }
    }
  }
}

class AddCommentSpec extends FlatSpec with ShouldMatchers with MockFactory{
  val Comment = AddComment
  
  "the AddComment command" should "instruct the builder to add a new comment" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (new Comment("invisible"))
    
    Comment{ "invisible" }
  }
}

class AddTextSpec extends FlatSpec with ShouldMatchers with MockFactory{
  val T = AddText
  
  "the AddText command" should "instruct the builder to add a new text node" in {
    implicit val b = mock[XmlBuilder]
    (b.addChild _) expects (new Text("this is text"))
    
    T{ "this is text" }
  }
}