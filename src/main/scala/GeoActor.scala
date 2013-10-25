package geo

import akka.actor._

class GeoActor extends Actor with ActorLogging {

  var buffer: Vector[GeoPoint] = Vector.empty
  val bufferSize = 20

  def receive = {
    case AddPoints(points) =>
      maintainBuffer(points)
      log.info(s"got points: $points")
      log.info(s"buffer: $buffer")
    case GetPoints =>
      sender ! buffer.sortBy(morton)
  }

  def maintainBuffer(points: Vector[GeoPoint]) = {
    buffer = (points ++ buffer).take(bufferSize)
  }

  def morton(g: GeoPoint) = Morton.morton(g.latitude.toInt + 400, g.longitude.toInt + 400)
}

case object GetPoints
case class AddPoints(points: Vector[GeoPoint])
