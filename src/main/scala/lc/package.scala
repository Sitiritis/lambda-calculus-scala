package object lc {
  def identity[T] = (x: T) => x

  type NaturalNumber[T] = ((T) => T) => (T) => T

  def get_n_function[T](n: Int): NaturalNumber[T] = {
    if (n == 0) {
      (f: (T) => T) => identity
    }
    else {
      (f: (T) => T) => (x: T) => f(get_n_function(n - 1)(f)(x))
    }
  }
}
