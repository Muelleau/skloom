package io.vigg.skloom

import java.util.concurrent.{ExecutorService, Executors, Future}

object SkLoom {

  implicit val executor: ExecutorService =
    Executors.newVirtualThreadPerTaskExecutor()

  def go(f: () => Unit): Future[Unit] = {
    executor.submit(() => f())
  }

  def goFuture[A, T](f: A => T, x: A): Future[T] = {
    executor.submit(() => f(x))
  }

}
