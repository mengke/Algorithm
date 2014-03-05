/*
 * Copyright 2014 mengke@me.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import sbt._
import Keys._
import scala.Some

object Build extends Build {

  import Dependencies._

  lazy val root = Project("AlgorithmProject",file("."))
    .aggregate(algorithm, algorithmTests)
    .settings(basicSettings: _*)
    .settings(noPublishing: _*)


  lazy val algorithm = Project("algorithm", file("algorithm"))
    .settings(algorithmModuleSettings: _*)

  lazy val algorithmTests = Project("algorithm-tests", file("algorithm-tests"))
    .dependsOn(algorithm)
    .settings(algorithmModuleSettings: _*)
    .settings(noPublishing: _*)
    .settings(libraryDependencies ++= test(scalatest, specs2))

  lazy val basicSettings = Seq(
    version               := "0.1.0",
    organization          := "io.github.mengke",
    organizationHomepage  := Some(new URL("http://mengke.github.io")),
    description           := "These are some algorithm exercises I found on the Internet, " +
                              "and I try to solve them with Scala. But I can't promise the solution is right.:)",
    startYear             := Some(2014),
    licenses              := Seq("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt")),
    scalaVersion          := "2.10.3",
    scalacOptions         := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.6",
      "-language:_",
      "-Xlog-reflective-calls"
    ),
    testOptions           := Seq(Tests.Filter(s => Seq("Spec", "Unit").exists(s.endsWith(_)))),
    parallelExecution in Test := false
  )

  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )

  lazy val algorithmModuleSettings =
    basicSettings



}