/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2002-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */

// generated by genprod on Sat Oct 16 11:19:09 PDT 2010  (with extra methods)

package scala




/** Function with 5 parameters.
 *
 */
trait Function5[-T1, -T2, -T3, -T4, -T5, +R] extends AnyRef { self =>
  def apply(v1:T1,v2:T2,v3:T3,v4:T4,v5:T5): R
  override def toString() = "<function5>"

  /** f(x1, x2, x3, x4, x5)  == (f.curried)(x1)(x2)(x3)(x4)(x5)
   */
  def curried: T1 => T2 => T3 => T4 => T5 => R = {
    (x1: T1) => ((x2: T2, x3: T3, x4: T4, x5: T5) => self.apply(x1, x2, x3, x4, x5)).curried
  }
  @deprecated("Use 'curried' instead")
  def curry = curried

  /* f(x1, x2, x3, x4, x5) == (f.tupled)(Tuple5(x1, x2, x3, x4, x5))
   */
  def tupled: Tuple5[T1, T2, T3, T4, T5] => R = {
    case Tuple5(x1, x2, x3, x4, x5) => apply(x1, x2, x3, x4, x5)
  }

}
