import AssemblyKeys._

name := "gosen filters"

organization := "jp.co.mixi.rd"

version := "0.2.0"

scalaVersion := "2.9.2"


libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1"
   ,"org.apache.lucene" % "lucene-core" % "4.0.0"
   ,"org.apache.solr" % "solr-core" % "4.0.0"
   ,"org.apache.lucene" % "lucene-gosen" % "4.0.0"
)

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

assemblySettings
