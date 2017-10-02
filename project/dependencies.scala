import sbt._, Keys._

object Dependencies {
  val servletApiVersion = "2.5"
  val logbackVersion    = "1.2.2"
  val http4sVersion     = "0.17.0"
  val catsVersion       = "0.9.0"
  val catsEffectVersion = "0.3"
  val fs2Version        = "0.9.7"
  val fs2CatsVersion    = "0.3.0"
  val circeVersion      = "0.8.0"

  object libraries {
    val cats = Seq(
      "org.typelevel" %% "cats"        % catsVersion,
      "org.typelevel" %% "cats-effect" % catsEffectVersion
    )

    val logback = Seq("ch.qos.logback" % "logback-classic" % logbackVersion)

    val fs2 = Seq(
      "co.fs2" %% "fs2-core" % fs2Version,
      "co.fs2" %% "fs2-io"   % fs2Version,
      "co.fs2" %% "fs2-cats" % fs2CatsVersion
    )

    val http4s = Seq(
      "org.http4s" %% "http4s-dsl",
      "org.http4s" %% "http4s-blaze-server",
      "org.http4s" %% "http4s-servlet",
      "org.http4s" %% "http4s-blaze-client",
      "org.http4s" %% "http4s-circe"
    ).map(_ % http4sVersion)

    val circe = Seq("io.circe" %% "circe-core",
                    "io.circe" %% "circe-generic",
                    "io.circe" %% "circe-literal",
                    "io.circe" %% "circe-parser")
      .map(_ % circeVersion)

    val servletApi = Seq("javax.servlet" % "servlet-api" % servletApiVersion % "provided")

    val sampleProject = cats ++ fs2 ++ http4s ++ circe ++ servletApi ++ logback
  }
}
