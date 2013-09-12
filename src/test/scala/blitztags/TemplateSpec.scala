package blitztags

import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import blitztags.AddElementCommands._

class TemplateSpec extends FlatSpec with ShouldMatchers with TemplateMatchers {
  val Books = new NormalElement("books")
  val Book = new NormalElement("book")
  val Author = new RawTextElement("author")
  val Keyword = new VoidElement("keyword")

  "a XML template" should "render XML documents" in {
    new Template {
      Book {
        Author { "Chris Cross" }
        Keyword('value -> "Crossword")
      }
    } should matchXml(
      <book><author>Chris Cross</author><keyword value="Crossword"/></book>)
  }

  it should "render sequential empty normal elements with end tag" in {
    new Template {
      Books {
        Book {}
        Book {}
      }
    } should matchXml(
      <books><book></book><book></book></books>)
  }
}