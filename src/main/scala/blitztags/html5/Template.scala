package blitztags.html5

import blitztags.BaseTemplate
import blitztags.Attributes

trait Template extends BaseTemplate with Tags with HtmlClasses {
  val doctype = "<!DOCTYPE html>"

  override def prettyPrint = {
    doctype + "\n" + super.prettyPrint
  }

  override def miniPrint = {
    doctype + "\n" + super.miniPrint
  }
}