import sbt._

class MpcBuild(info: ProjectInfo) extends DefaultWebProject(info) {
  val jetty7Webapp = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "compile;test"
  val jetty7Server = "org.eclipse.jetty" % "jetty-server" % "7.0.2.RC0" % "compile;test"
  val jersey = "com.sun.jersey" % "jersey-server" % "1.5"
  val jerseyJson = "com.sun.jersey" % "jersey-json" % "1.5"
  val mpd = "org.reliant" % "scalampd_2.8.1" % "1.0" % "compile"

  val javaNetRepo = "Java.net Repository for Maven" at "http://download.java.net/maven/2/"
}
