import AssemblyKeys._

name := "gosen-filters"

organization := "jp.co.mixi.rd"

version := "4.6.0"

scalaVersion := "2.10.3"


libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.9.2" % "test"
   ,"org.apache.lucene" % "lucene-core" % "4.6.0"
   ,"org.apache.solr" % "solr-core" % "4.6.0"
   ,"org.apache.lucene" % "lucene-gosen" % "4.6.0"
)

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

assemblySettings
