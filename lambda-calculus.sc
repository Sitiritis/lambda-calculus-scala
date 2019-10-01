def identity[T] = (x: T) => x;

def get_n_function[T](n: Int): ((T) => T) => (T) => T = {
  if (n == 0) {
    (f: (T) => T) => identity;
  }
  else {
    (f: (T) => T) => (x: T) => f(get_n_function(n - 1)(f)(x));
  }
}

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

And[Int](new True[BooleanValue[Int]])(new True[Int])
And[Int](new True[BooleanValue[Int]])(new False[Int])
And[Int](new False[BooleanValue[Int]])(new False[Int])
And[Int](new False[BooleanValue[Int]])(new True[Int])

def Or[T] =
  (c1: BooleanValue[BooleanValue[T]]) =>
  (c2: BooleanValue[T]) =>
  c1(new True[T])(c2)

Or[Int](new True[BooleanValue[Int]])(new True[Int])
Or[Int](new True[BooleanValue[Int]])(new False[Int])
Or[Int](new False[BooleanValue[Int]])(new False[Int])
Or[Int](new False[BooleanValue[Int]])(new True[Int])
