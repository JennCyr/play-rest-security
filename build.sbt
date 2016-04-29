lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

name := "play-rest-security"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  evolutions,
  "org.webjars" % "jquery" % "2.2.1",
  "com.wordnik" %% "swagger-play2" % "1.3.12" exclude("org.reflections", "reflections"), "org.reflections" % "reflections" % "0.9.8" notTransitive (), "org.webjars" % "swagger-ui" % "2.1.8-M1"

)

routesGenerator := InjectedRoutesGenerator
