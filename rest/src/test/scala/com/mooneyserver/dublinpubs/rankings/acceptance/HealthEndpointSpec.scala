package com.mooneyserver.dublinpubs.rankings.acceptance

import play.api.libs.ws.ning.NingWSClient

class HealthEndpointSpec extends BaseSpec {

   info("As a service owner")
   info("I want to be able to check the health of the service")
   info("So that I can include this service in the load balancers pool")

   feature("Service health Endpoint") {
     scenario("A load balancer queries the health endpoint of the service") {
       Given("The URL of the health endpoint for the deployed service")
         val healthEndpointURL = rankingServiceBaseUrl + "/rest/health"

       When("We send a simple GET request to the URL")
         val wsClient = NingWSClient()
         val futureResp = wsClient.url(healthEndpointURL).get()

       Then("We receive a 200 response")
         futureResp.map(
           wsResponse =>
             wsResponse.status shouldBe 200
         )
     }
   }
 }
