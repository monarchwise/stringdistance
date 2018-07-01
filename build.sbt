import xerial.sbt.Sonatype._

name := "stringdistance"
version := "1.0.2"
scalaVersion := "2.12.6"
organization := "org.stringdistance"
sonatypeProjectHosting := Some(GitHubHosting("vickumar1981", "stringdistance", "vickumar@gmail.com"))
publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
pomIncludeRepository := { _ => false }
publishArtifact in Test := false

// Add sonatype repository settings
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

assemblyJarName := "stringdistance_2.12-" + version.value + ".jar"
test in assembly := {}

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.withClassifier(Some(version.value))
}
addArtifact(artifact in (Compile, assembly), assembly)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1" % Test
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % Test

coverageEnabled := true
coverageMinimum := 100
coverageFailOnMinimum := true