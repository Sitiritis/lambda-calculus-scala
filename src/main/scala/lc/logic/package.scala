package lc

package object logic {
  trait BooleanValue[T] {
    def apply(x: T)(y: T): T
  }

  class True[T] extends BooleanValue[T] {
    override def apply(x: T)(y: T): T = x;
  }

  class False[T] extends BooleanValue[T] {
    override def apply(x: T)(y: T): T = y;
  }

  def If[T] =
    (c: BooleanValue[T]) =>
      (x: T) =>
        (y: T) =>
          c(x)(y)

  def And[T] =
    (c1: BooleanValue[BooleanValue[T]]) =>
      (c2: BooleanValue[T]) =>
        c1(c2)(new False[T])

  def Or[T] =
    (c1: BooleanValue[BooleanValue[T]]) =>
      (c2: BooleanValue[T]) =>
        c1(new True[T])(c2)
}
