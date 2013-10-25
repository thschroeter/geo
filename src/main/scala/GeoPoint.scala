package geo

import spray.json._
import DefaultJsonProtocol._

// TODO: validation
case class GeoPoint(latitude: Double, longitude: Double)

object GeoPointJsonProtocol extends DefaultJsonProtocol {
  implicit val geoPointFormat = jsonFormat2(GeoPoint)
}