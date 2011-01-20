/*                     __                                               *\
**     ________ ___   / /  ___     Scala API                            **
**    / __/ __// _ | / /  / _ |    (c) 2003-2011, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    http://scala-lang.org/               **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package scala.concurrent

import java.util.concurrent.{ExecutorService, Callable, TimeUnit}

/** The <code>ThreadPoolRunner</code> trait uses
 *  a <code>java.util.concurrent.ExecutorService</code>
 *  to run submitted tasks.
 *
 *  @author Philipp Haller
 */
trait ThreadPoolRunner extends FutureTaskRunner {

  type Task[T] = Callable[T] with Runnable
  type Future[T] = java.util.concurrent.Future[T]

  private class RunCallable[S](fun: () => S) extends Runnable with Callable[S] {
    def run() = fun()
    def call() = fun()
  }

  implicit def functionAsTask[S](fun: () => S): Task[S] =
    new RunCallable(fun)

  implicit def futureAsFunction[S](x: Future[S]): () => S =
    () => x.get()

  protected def executor: ExecutorService

  def submit[S](task: Task[S]): Future[S] = {
    executor.submit[S](task)
  }

  def execute[S](task: Task[S]) {
    executor execute task
  }

  def managedBlock(blocker: ManagedBlocker) {
    blocker.block()
  }

}
