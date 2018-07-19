package net.hallatech.gradle.plugins

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class TaskExecutionSpec extends BaseSpecification {

  def "executes assembly task for single configured assembly"() {
    given:
    buildFile << """
      runAssembler {
        storefront {
          outputFileName = "ATGStorefront.ear"
        }
      }
"""
    when:
    def result = runGradleTask("assembleStorefront")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.task(":assembleStorefront").outcome == SUCCESS
  }

  def "executes assembly tasks for multiple configured assembly"() {
    given:
    buildFile << """
      runAssembler {
        storefront {
          outputFileName = "ATGStorefront.ear"
        }
        bcc {
          outputFileName = "ATGBCC.ear"
        }
        csc {
          outputFileName = "ATGService.ear"
        }
        lockserver {
          outputFileName = "ATGLockserver.ear"
        }
      }
"""
    when:
    def result = runGradleTask("assembleStorefront","assembleBcc","assembleCsc","assembleLockserver")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.output.contains("Assembling ATGBCC.ear")
    result.output.contains("Assembling ATGService.ear")
    result.output.contains("Assembling ATGLockserver.ear")
    result.task(":assembleStorefront").outcome == SUCCESS
    result.task(":assembleBcc").outcome == SUCCESS
    result.task(":assembleCsc").outcome == SUCCESS
    result.task(":assembleLockserver").outcome == SUCCESS
  }

  def "execute assembleAll task executes all configured assemblies"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
      }
      bcc {
        outputFileName = "ATGBCC.ear"
      }
      csc {
        outputFileName = "ATGService.ear"
      }
      lockserver {
        outputFileName = "ATGLockserver.ear"
      }
    }
"""
    when:
    def result = runGradleTask("assembleAll")
    then:
    result.output.contains("Assembling ATGStorefront.ear")
    result.output.contains("Assembling ATGBCC.ear")
    result.output.contains("Assembling ATGService.ear")
    result.output.contains("Assembling ATGLockserver.ear")
    result.task(":assembleAll").outcome == SUCCESS
  }

}
