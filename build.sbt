import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.cross.CrossType
import play.PlayScala
import playscalajs.ScalaJSPlay
import sbt.Project.projectToRef

lazy val clients = Seq(scalajsClient)
lazy val scalaV = "2.11.6"

lazy val playServer = (project in file("play-server")).settings(
  scalaVersion := scalaV,
  scalaJSProjects := clients,
  pipelineStages := Seq(scalaJSProd, gzip),
  libraryDependencies ++= Seq(
    "com.vmunier" %% "play-scalajs-scripts" % "0.2.1",
    "org.webjars" % "jquery" % "1.11.1",
    "org.webjars" % "chartjs"  % "1.0.1"
  )
).enablePlugins(PlayScala).
  aggregate(clients.map(projectToRef): _*).
  dependsOn(sharedJvm)

lazy val scalajsClient = (project in file("scalajs-client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,
  sourceMapsDirectories += sharedJs.base / "..",
  unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
  jsDependencies += "org.webjars" % "chartjs"  % "1.0.1" / "Chart.js",
  skip in packageJSDependencies := false,
  libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSPlay).
  dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared")).
  settings(scalaVersion := scalaV).
  jsConfigure(_ enablePlugins ScalaJSPlay).
  jsSettings(sourceMapsBase := baseDirectory.value / "..")

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js



// loads the Play project at sbt startup
onLoad in Global := (Command.process("project playServer", _: State)) compose (onLoad in Global).value
