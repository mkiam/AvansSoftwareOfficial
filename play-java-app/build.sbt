name := """play-java-intro"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean
)     

libraryDependencies ++= Seq(
  javaWs
)

fork in run := true

fork in run := true

fork in run := true