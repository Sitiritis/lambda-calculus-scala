package lc

import lc.logic.True

package object omega {
  // Here, the small_omega combination should be present, but
  // it is impossible to represent it with a strong typing system directly,
  // which is a case for Scala and Haskell. For more information see:
  // https://en.wikipedia.org/wiki/Simply_typed_lambda_calculus#General_observations
  // https://stackoverflow.com/questions/33546004/is-it-possible-to-define-omega-combinator-%CE%BBx-xx-in-modern-haskell
  // Hence, big_omega also cannot be implemented directly.

  class SmallOmegaTyped {
    def apply(w: SmallOmegaTyped): SmallOmegaTyped = w(w)
  }

  def big_omega(): SmallOmegaTyped =
    new SmallOmegaTyped()(new SmallOmegaTyped())

  def infinite_graph_non_reducible_example(): SmallOmegaTyped =
    new SmallOmegaTyped()(new SmallOmegaTyped()(new SmallOmegaTyped()))

  def infinite_graph_reducible_example_auxilary[T](x: => SmallOmegaTyped): True[T] =
    new True[T]

  def infinite_graph_reducible_example[T](): True[T] =
    infinite_graph_reducible_example_auxilary(infinite_graph_non_reducible_example())
}
