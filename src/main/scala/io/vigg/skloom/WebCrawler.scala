package io.vigg.skloom

import io.vigg.skloom.SkLoom._
import requests.Response

import java.util.concurrent.Future

class WebCrawler {

  def crawlList(lst: List[String]): Seq[Future[Unit]] = {

    lst.map(url => {
      go(() => {
        val x: Response = requests.get("https://google.com")
        println(x.text())
      })
    })

  }

}
