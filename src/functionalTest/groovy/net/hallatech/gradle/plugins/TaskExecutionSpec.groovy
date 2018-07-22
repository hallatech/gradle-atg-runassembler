package net.hallatech.gradle.plugins

import org.gradle.api.GradleException
import org.gradle.testkit.runner.UnexpectedBuildFailure

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS
import static org.gradle.testkit.runner.TaskOutcome.FAILED

class TaskExecutionSpec extends BaseSpecification {

  def "executes runAssembler task for single configured assembly"() {
    given:
    buildFile << """
      runAssembler {
        storefront {
          outputFileName = "ATGStorefront.ear"
          modules = ["DPS"]
        }
      }
"""
    when:
    def result = runGradleTask("runAssembleStorefront","-i")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

  def "executes runAssembler tasks for multiple configured assembly"() {
    given:
    buildFile << """
      runAssembler {
        storefront {
          outputFileName = "ATGStorefront.ear"
          modules = ["DPS"]
        }
        bcc {
          outputFileName = "ATGBCC.ear"
          modules = ["DPS"]
        }
        csc {
          outputFileName = "ATGService.ear"
          modules = ["DPS"]
        }
        lockserver {
          outputFileName = "ATGLockserver.ear"
          modules = ["DPS"]
        }
      }
"""
    when:
    def result = runGradleTask("runAssembleStorefront","runAssembleBcc","runAssembleCsc","runAssembleLockserver","-i")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.output.contains("Assembling ATGBCC.ear")
    result.output.contains("Assembling ATGService.ear")
    result.output.contains("Assembling ATGLockserver.ear")
    result.task(":runAssembleStorefront").outcome == SUCCESS
    result.task(":runAssembleBcc").outcome == SUCCESS
    result.task(":runAssembleCsc").outcome == SUCCESS
    result.task(":runAssembleLockserver").outcome == SUCCESS
  }

  def "execute runAssembleAll task executes all configured assemblies"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS"]
      }
      bcc {
        outputFileName = "ATGBCC.ear"
        modules = ["DPS"]
      }
      csc {
        outputFileName = "ATGService.ear"
        modules = ["DPS"]
      }
      lockserver {
        outputFileName = "ATGLockserver.ear"
        modules = ["DPS"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleAll","-i")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.output.contains("Assembling ATGBCC.ear")
    result.output.contains("Assembling ATGService.ear")
    result.output.contains("Assembling ATGLockserver.ear")
    result.task(":runAssembleAll").outcome == SUCCESS
  }

  def "execute runAssembleStorefront task with a minimum single module"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    result.output.contains("ATGStorefront.ear -m DPS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

  def "execute runAssembleStorefront task with a multiple modules"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    result.output.contains("ATGStorefront.ear -m DPS DSS DCS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

  def "execute runAssembleStorefront task with a single layer"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        layers = ["prod"]
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    result.output.contains("ATGStorefront.ear -layer prod -m DPS DSS DCS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

  def "execute runAssembleStorefront task with jboss and standalone options"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        layers = ["prod"]
        jboss = true
        standalone = true
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    result.output.contains("-standalone -jboss")
    result.output.contains("ATGStorefront.ear -layer prod -m DPS DSS DCS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

}