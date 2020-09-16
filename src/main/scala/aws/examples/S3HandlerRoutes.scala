package aws.examples

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import spray.json.{DefaultJsonProtocol, pimpAny}
import akka.http.scaladsl.Http


trait S3handlerFormats extends DefaultJsonProtocol {
   implicit val listBucketFormat = jsonFormat1(ObjectList)
}

object S3HandlerRoutes extends App with S3handlerFormats {

  implicit val system = ActorSystem("DirectivesBreakdown")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher
  import akka.http.scaladsl.server.Directives._

  val listRoute = pathPrefix("api" / "bucket" / Segment) { bucket_name =>
    get{
      val itemsList = new S3Handler().listBucket(bucket_name)
      complete(itemsList.toJson.prettyPrint)
    }
  }

  Http().bindAndHandle(listRoute, "localhost", 8080)

}
