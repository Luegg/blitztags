package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import scala.xml.Text

class XmlBuilderSpec extends FlatSpec with ShouldMatchers {
  "A XmlBuilder" should "construct XML Documents" in {
    val b = new XmlBuilder{}
    b.startElement(<html lang="de"/>)
    b.addChild(new Text("Hello world"))
    b.endElement
    
    b.document.docElem should be (<html lang="de">Hello world</html>)
  }
  
  it should "add multiple children to an element" in {
    val b = new XmlBuilder{}
    b.startElement(<html/>)
    b.addChild(<a/>)
    b.addChild(<b/>)
    b.addChild(<c/>)
    b.endElement
    
    b.document.docElem should be (<html><a/><b/><c/></html>)
  }
  
  it should "build nested documents" in {
    val b = new XmlBuilder{}
    b.startElement(<first/>)
    b.startElement(<second/>)
    b.startElement(<third/>)
    b.addChild(new Text("3"))
    b.endElement
    b.startElement(<fourth/>)
    b.addChild(new Text("4"))
    b.endElement
    b.endElement
    b.endElement
    
    b.document.docElem should be (<first><second><third>3</third><fourth>4</fourth></second></first>)
  }
  
  it should "build sequential empty nodes" in {
    val b = new XmlBuilder{}
    b.startElement(<first/>)
    b.startElement(<second/>)
    b.endElement
    b.startElement(<third/>)
    b.endElement
    b.endElement
    
    b.document.docElem should be (<first><second/><third/></first>)
  }
}