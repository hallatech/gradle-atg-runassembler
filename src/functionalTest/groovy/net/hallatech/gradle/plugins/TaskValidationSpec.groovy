package net.hallatech.gradle.plugins

import org.gradle.testkit.runner.UnexpectedBuildFailure

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class TaskValidationSpec extends BaseSpecification {

  def "conflicting runInPlace and collapseClassPath options throws RunAssemblerConfigurationException"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        collapseClassPath = true
        runInPlace = true
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    UnexpectedBuildFailure exception = thrown()
    exception.message.contains(
        "The runAssembler {} configuration is invalid: collapseClassPath and runInPlace cannot be used together")
  }

  def "assembler task fails when collapseExcludeDirs configured without required collapseClassPath"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        collapseExcludeDirs = ["dir1","dir2"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    UnexpectedBuildFailure exception = thrown()
    exception.message.contains(
        "The runAssembler {} configuration is invalid: collapseClassPath is required if getCollapseExcludeDirs is set.")
  }

  def "assembler task fails when collapseExcludeFiles configured without required collapseClassPath"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        collapseExcludeFiles = ["file1","file2"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    UnexpectedBuildFailure exception = thrown()
    exception.message.contains(
        "The runAssembler {} configuration is invalid: collapseClassPath is required if getCollapseExcludeFiles is set.")
  }

  def "assembler task fails when runInPlace is configured without jboss"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        runInPlace = true
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    UnexpectedBuildFailure exception = thrown()
    exception.message.contains(
        "The runAssembler {} configuration is invalid: runInPlace cannot be used without jboss.")
  }



}