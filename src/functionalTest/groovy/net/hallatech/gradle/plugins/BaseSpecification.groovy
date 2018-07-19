package net.hallatech.gradle.plugins

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

class BaseSpecification extends Specification {

  @Rule
  final TemporaryFolder testProjectDir = new TemporaryFolder()
  protected File buildFile

  def setup() {
    buildFile = testProjectDir.newFile('build.gradle')
    buildFile << """
    plugins {
      id 'net.hallatech.atg-runassembler'
    }
"""
  }

  protected BuildResult runGradleTask(String... args) {
    GradleRunner.create()
        .withProjectDir(testProjectDir.root)
        .withArguments(args)
        .withPluginClasspath()
        .build()
  }
}
