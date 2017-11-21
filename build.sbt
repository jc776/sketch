scalaVersion in ThisBuild := "2.12.3"
//resolvers in ThisBuild += Resolver.sonatypeRepo("releases")

val circeVersion = "0.8.0"
val slinkyVersion = "0.1.1+27-c39fb5e8" //"0.1.1"

val client = project.in(file("client"))
  .settings(
    name := "sketch",
    addCompilerPlugin("org.scalameta" % "paradise" % "3.0.0-M10" cross CrossVersion.full),
    libraryDependencies ++= Seq(
      "io.circe" %%% "circe-core" % circeVersion,
      "io.circe" %%% "circe-generic" % circeVersion,
      "io.circe" %%% "circe-parser" % circeVersion,
      "me.shadaj" %%% "slinky-core" % slinkyVersion,
      "me.shadaj" %%% "slinky-web" % slinkyVersion,
      "me.shadaj" %%% "slinky-hot" % slinkyVersion
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
	  scalacOptions += "-P:scalajs:sjsDefinedByDefault",
    watchSources := { Seq(WatchSource(baseDirectory.value / "src")) } // * 1
  )
  .enablePlugins(ScalaJSPlugin)

scalacOptions in ThisBuild ++= Seq(
  "-target:jvm-1.8",
  "-encoding", "UTF-8",
  "-unchecked",
  "-feature",
  "-deprecation",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused",
  "-Ymacro-expand:normal" // Scala-IDE implicits?
)

// * 1. sbt keeps locking node_modules/ and Eclipse directories.
watchSources := { Seq() } 

// * 2. "This file is derived, are you sure you want to edit it?"
// EclipseKeys.eclipseOutput in ThisBuild := Some("target-ide")
