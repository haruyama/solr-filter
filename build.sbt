import AssemblyKeys._

name := "gosen-filters"

organization := "jp.co.mixi.rd"

version := "4.5.1"

scalaVersion := "2.10.3"


libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.9.2" % "test"
   ,"org.apache.lucene" % "lucene-core" % "4.5.1"
   ,"org.apache.solr" % "solr-core" % "4.5.1"
   ,"org.apache.lucene" % "lucene-gosen" % "4.5.1"
)

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

assemblySettings
