package geo

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import spray.httpx.SprayJsonSupport._
import StatusCodes._

import GeoPointJsonProtocol._

class GeoServiceSpec extends Specification with Specs2RouteTest with GeoService {
  def actorRefFactory = system

  "GeoService" should {
    "accept geo points" in {
      Post("/geopoints", Vector(GeoPoint(0d, 0d))) ~> geoRoute ~> check { responseAs[String] must contain("ok") } 
    }
    "serve geo points" in {
      Get("/geopoints") ~> geoRoute ~> check { responseAs[Vector[GeoPoint]] must be(Vector()) }
    }
  }
}