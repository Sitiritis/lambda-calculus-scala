def identity[T] = (x: T) => x;

def get_n_function[T](n: Int): ((T) => T) => (T) => T = {
  if (n == 0) {
    (f: (T) => T) => identity;
  }
  else {
    (f: (T) => T) => (x: T) => f(get_n_function(n - 1)(f)(x));
  }
}

// n example
get_n_function[Int](5)(_ + 1)(0)

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

// If example
If(new True[Int])(1)(2)
If(new False[Int])(1)(2)

def And[T] =
  (c1: BooleanValue[BooleanValue[T]]) =>
  (c2: BooleanValue[T]) =>
  c1(c2)(new False[T])

// And example
And[Int](new True[BooleanValue[Int]])(new True[Int])
And[Int](new True[BooleanValue[Int]])(new False[Int])
And[Int](new False[BooleanValue[Int]])(new False[Int])
And[Int](new False[BooleanValue[Int]])(new True[Int])

def Or[T] =
  (c1: BooleanValue[BooleanValue[T]]) =>
  (c2: BooleanValue[T]) =>
  c1(new True[T])(c2)

// Or example
Or[Int](new True[BooleanValue[Int]])(new True[Int])
Or[Int](new True[BooleanValue[Int]])(new False[Int])
Or[Int](new False[BooleanValue[Int]])(new False[Int])
Or[Int](new False[BooleanValue[Int]])(new True[Int])

// Here, the small_omega combination should be present, but
// it is impossible to represent it with a strong typing system,
// which is a case for Scala and Haskell. For more information see:
// https://en.wikipedia.org/wiki/Simply_typed_lambda_calculus#General_observations
// https://stackoverflow.com/questions/33546004/is-it-possible-to-define-omega-combinator-%CE%BBx-xx-in-modern-haskell
// Hence, big_omega also cannot be implemented directly.
