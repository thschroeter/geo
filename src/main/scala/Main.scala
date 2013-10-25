package geo

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {

  implicit val system = ActorSystem()

  val service = system.actorOf(Props[GeoServiceActor], "geo-service")

  IO(Http) ! Http.Bind(service, "localhost", port = 8080)
}
