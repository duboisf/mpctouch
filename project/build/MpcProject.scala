import sbt._

class MpcBuild(info: ProjectInfo) extends DefaultWebProject(info) {
  val jetty7Webapp = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "compile;test"
  val jetty7Server = "org.eclipse.jetty" % "jetty-server" % "7.0.2.RC0" % "compile;test"
  val mpd = "org.reliant" % "scalampd_2.8.1" % "1.0" % "compile"
  val spade = "org.atmosphere" % "atmosphere-spade-server" % "0.7"
  val grizzled = "org.clapper" %% "grizzled-slf4j" % "0.4"
  val simple = "org.slf4j" % "slf4j-simple" % "1.6.1"
  val javaNetRepo = "Java.net Repository for Maven" at "http://download.java.net/maven/2/"

  override def scanDirectories = Nil
}
