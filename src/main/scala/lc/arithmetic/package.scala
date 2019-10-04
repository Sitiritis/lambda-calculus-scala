package lc

package object arithmetic {
  def successor_1[T](n: NaturalNumber[T]): NaturalNumber[T] =
    (f: (T) => T) =>
      (x: T) =>
        f(n(f)(x))

  def successor_2[T](n: NaturalNumber[T]): NaturalNumber[T] =
    (f: (T) => T) =>
      (x: T) =>
        n(f)(f(x))

  def sum_1[T](n: NaturalNumber[T])(m: NaturalNumber[T]): NaturalNumber[T] =
    (f: (T) => T) => (x: T) => m(f)(n(f)(x))

  def sum_2[T](n: NaturalNumber[NaturalNumber[T]])(m: NaturalNumber[T]): NaturalNumber[T] =
    n(successor_1)(m)
}
