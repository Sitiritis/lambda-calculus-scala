object Main extends App {
  import lc._

  // n example
  val five = get_n_function[Int](5)
  println(five(_ + 1)(0))


  // Arithmetic functions
  import lc.arithmetic._

  // Successors
  val six = successor_1(five)
  println(six(_ + 1)(0))
  val seven = successor_2(six)
  println(seven(_ + 1)(0))

  // Addition
  println(sum_1[Int](six)(five)(_ + 1)(0))
  println(sum_2[Int](get_n_function[NaturalNumber[Int]](10))(five)(_ + 1)(0))


  // Logic
  import lc.logic._

  // If example
  println(If(new True[Int])(1)(2))
  println(If(new False[Int])(1)(2))

  // And example
  println(And[Int](new False[BooleanValue[Int]])(new False[Int]))
  println(And[Int](new False[BooleanValue[Int]])(new True[Int]))
  println(And[Int](new True[BooleanValue[Int]])(new False[Int]))
  println(And[Int](new True[BooleanValue[Int]])(new True[Int]))

  // Or example
  println(Or[Int](new False[BooleanValue[Int]])(new False[Int]))
  println(Or[Int](new False[BooleanValue[Int]])(new True[Int]))
  println(Or[Int](new True[BooleanValue[Int]])(new False[Int]))
  println(Or[Int](new True[BooleanValue[Int]])(new True[Int]))


  // Omega combinators
  import lc.omega._

  // Does not terminate, so the call will result in a stack overflow
  try {
    big_omega()
  }
  catch {
    case _: StackOverflowError => println("Stack overflow has happened")
    case _ => println("Unknown error has happened")
  }

  // Does not terminate, so the call will result in a stack overflow
  try {
    infinite_graph_non_reducible_example()
  }
  catch {
    case _: StackOverflowError => println("Stack overflow has happened")
    case _ => println("Unknown error has happened")
  }

  // Terminates, since the expression is reducible; though the reduction graph is infinite
  println(infinite_graph_reducible_example())
}
