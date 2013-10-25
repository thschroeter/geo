package geo

import scala.concurrent.duration._
import akka.actor._
import akka.pattern.ask
import akka.util.Timeout
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport._
import GeoPointJsonProtocol._

class GeoServiceActor extends Actor with GeoService {

  def actorRefFactory = context

  def receive = runRoute(geoRoute)

}

trait GeoService extends HttpService {

  val geoActor = actorRefFactory.actorOf(Props[GeoActor], "geo-actor")

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout: Timeout = 5.seconds

  val geoRoute = {
    get { 
      path("") {
        complete(<h1>Hello</h1>)
      }
    } ~
    pathPrefix("assets") {
      getFromDirectory("assets")
    } ~
    path("geopoints") {
      post {
        entity(as[Vector[GeoPoint]]) { geoPoints =>
          geoActor ! AddPoints(geoPoints) 
          complete("ok")
        }
      } ~
      get {
        complete {
          geoActor.ask(GetPoints).mapTo[Vector[GeoPoint]]
        }
      }
    }
  }
} 
