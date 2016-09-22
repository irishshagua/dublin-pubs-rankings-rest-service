package com.mooneyserver.dublinpubs.rankings.acceptance

import org.scalatest.{AsyncFeatureSpec, GivenWhenThen, Matchers}

trait BaseSpec extends AsyncFeatureSpec with GivenWhenThen with Matchers {

  def rankingServiceBaseUrl: String =
    s"http://${System.getProperty("ranking.svc.host")}:${System.getProperty("ranking.svc.port")}"
}
