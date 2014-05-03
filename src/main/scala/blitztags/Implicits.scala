package blitztags

trait Implicits {
  implicit def attrBuilder(s: Symbol) = new{
    def ->(v: Any) =
      new AddElementCommands.AttrPair(s -> v.toString)
  }
}