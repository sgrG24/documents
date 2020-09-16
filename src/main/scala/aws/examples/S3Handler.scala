package aws.examples

import java.io.File

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.twitter.scalding.Args

import scala.collection.JavaConverters.asScalaBufferConverter

case class ObjectList(list: List[String])

class S3Handler{
  def listBucket(bucket_name: String) = {
    val s3Client = AmazonS3ClientBuilder.standard()
      .withRegion(Regions.US_EAST_1)
      .build()

    val result = s3Client.listObjectsV2(bucket_name).getObjectSummaries.asScala.toList

    val objectList = for{
      item <- result
    } yield item.getKey

    ObjectList(objectList)
  }
}

object S3Handler {

  def main(args: Array[String]): Unit = {
    val cmdArgs = Args(args)
    val bucket_name = cmdArgs("bucket_name")
    val file_path = cmdArgs("file_path")
    val key_name = "sagar/s3files/"

    val s3Client = AmazonS3ClientBuilder.standard()
      .withRegion(Regions.US_EAST_1)
      .build()

//    println(s"Uploading $file_path to $bucket_name")
//    try{
//      s3Client.putObject(bucket_name, key_name, file_path)
//    }
//    catch {
//      case e => println(e.getMessage)
//    }

    println(s"Listing object for $bucket_name")
    try{
      println(s3Client.listObjectsV2(bucket_name).getObjectSummaries)
    }
    catch {
      case e => println(e.getMessage)
    }
  }
}
