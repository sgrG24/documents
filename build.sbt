name := "documents"

version := "0.1"


lazy val sparkApp = (project in file("."))
  .settings(
    name := "documents",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.12.11",
    libraryDependencies ++= Seq(awsSdk, awsSdkS3, scaldingArgs) ++ akkaStack
  )

val awsSDKVersion = "1.11.749"
val akkaStreamVersion = "2.5.1"
val akkaHttpVersion = "10.0.6"


val scaldingArgs = "com.twitter" %% "scalding-args" % "0.17.3" % "compile"

lazy val awsSdk     = "com.amazonaws" % "aws-java-sdk-core" % awsSDKVersion
lazy val awsSdkS3   = "com.amazonaws" % "aws-java-sdk-s3" % awsSDKVersion

val akkaHttpCore    = "com.typesafe.akka"             %% "akka-http-core"                 % akkaHttpVersion
val akkaHttp        = "com.typesafe.akka"             %% "akka-http"                      % akkaHttpVersion
val akkaStream      = "com.typesafe.akka"             %% "akka-stream"                    % akkaStreamVersion
val sprayJsonAkka   = "com.typesafe.akka"             %% "akka-http-spray-json"           % akkaHttpVersion
val akkaHttpTestkit = "com.typesafe.akka"             %% "akka-http-testkit"              % akkaHttpVersion
val akkaStack       = Seq(akkaHttpCore, akkaHttp, akkaStream, sprayJsonAkka, akkaHttpTestkit)