package net.hallatech.gradle.plugins

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class TasksListingSpec extends BaseSpecification {

  def "included plugin prints description to console"() {
    when:
    def result = runGradleTask("tasks", "-i")

    then:
    result.output.contains("Oracle Commerce (ATG) runAssembler wrapper plugin")
    result.task(":tasks").outcome == SUCCESS
  }

  def "single unconfigured assembly in tasks listing shows task name"() {
    given:
    buildFile << """
      runAssembler {
        storefront {}
      }
"""
    when:
    def result = runGradleTask("tasks")

    then:
    result.output.contains("assembleStorefront")
    result.task(":tasks").outcome == SUCCESS
  }

  def "multiple unconfigured assembly in tasks listing shows task names"() {
    given:
    buildFile << """
      runAssembler {
        storefront {}
        bcc {}
        csc {}
        lockserver {}
      }
"""
    when:
    def result = runGradleTask("tasks")

    then:
    result.output.contains("assembleStorefront")
    result.output.contains("assembleBcc")
    result.output.contains("assembleCsc")
    result.output.contains("assembleLockserver")
    result.task(":tasks").outcome == SUCCESS
  }

  def "when multiple assemblies configured assembleAll task is listed  in tasks listing"() {
    given:
    buildFile << """
      runAssembler {
        storefront {}
        bcc {}
        csc {}
        lockserver {}
      }
"""
    when:
    def result = runGradleTask("tasks")

    then:
    result.output.contains("assembleAll")
    result.task(":tasks").outcome == SUCCESS
  }

}
