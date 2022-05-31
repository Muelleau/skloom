package io.vigg

import java.util.concurrent.{ExecutorService, Executors, Future}

package object skloom {

  implicit val executor: ExecutorService =
    Executors.newVirtualThreadPerTaskExecutor()

  def go(cmd: Runnable): Unit = executor.submit(cmd)

  def go(f: () => Unit): Future[Unit] =
    executor.submit(() => f())

  def goFuture[A, T](f: A => T, x: A): Future[T] = {
    executor.submit(() => { f(x) })
  }

  def >(f: () => Unit): Future[Unit] =
    executor.submit(() => f)

  def >[A, T](f: A => T, x: A): Future[T] = {
    executor.submit(() => { f(x) })
  }

}
