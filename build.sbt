import AssemblyKeys._

name := "gosen filters"

organization := "jp.co.mixi.rd"

version := "0.1.1"

scalaVersion := "2.9.1"


libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1"
   ,"org.apache.lucene" % "lucene-core" % "3.5.0"
   ,"org.apache.solr" % "solr-core" % "3.5.0"
   ,"org.apache.lucene" % "lucene-gosen" % "1.2.1"
)

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

seq(assemblySettings: _*)
