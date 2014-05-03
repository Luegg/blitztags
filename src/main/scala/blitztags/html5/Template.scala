package blitztags.html5

trait Template extends blitztags.BaseTemplate{
  val doctype = "<!DOCTYPE html>"
    
  override def prettyPrint = {
    doctype + "\n" + super.prettyPrint
  }
    
  override def miniPrint = {
    doctype + "\n" + super.miniPrint
  }
}