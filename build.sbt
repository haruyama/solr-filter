import AssemblyKeys._

name := "gosen-filters"

organization := "jp.co.mixi.rd"

version := "0.4.1"

scalaVersion := "2.10.2"


libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.9.1" % "test"
   ,"org.apache.lucene" % "lucene-core" % "4.3.1"
   ,"org.apache.solr" % "solr-core" % "4.3.1"
   ,"org.apache.lucene" % "lucene-gosen" % "4.3.1"
)

scalacOptions ++= Seq( "-deprecation", "-unchecked" )

assemblySettings
