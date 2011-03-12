import sbt._

class MpcBuild(info: ProjectInfo) extends DefaultWebProject(info) {
  val atmover = "0.7"

  val jetty7Webapp = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "compile;test"
  val jetty7Server = "org.eclipse.jetty" % "jetty-server" % "7.0.2.RC0" % "compile;test"

  val mpd = "org.reliant" % "scalampd_2.8.1" % "1.0" % "compile"
  val spade = "org.atmosphere" % "atmosphere-spade-server" % atmover
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.4"
  val simple = "org.slf4j" % "slf4j-simple" % "1.6.1"

  val atmoruntime = "org.atmosphere" % "atmosphere-runtime" % atmover withSources
  val atmojersey = "org.atmosphere" % "atmosphere-jersey" % atmover withSources

  val javanetrepo = "Java.net Repository for Maven" at "http://download.java.net/maven/2/"
  val javanetrepo1 = "Java.net Repository 1 for Maven (legacy)" at "http://download.java.net/maven/1/"

  val jerseyserver = "com.sun.jersey" % "jersey-server" % "1.5" withSources

  override def scanDirectories = Nil
}
