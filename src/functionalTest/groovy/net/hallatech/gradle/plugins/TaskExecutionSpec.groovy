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
    def result = runGradleTask("runAssembleStorefront")
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
    def result = runGradleTask("runAssembleStorefront","runAssembleBcc","runAssembleCsc","runAssembleLockserver")
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
    def result = runGradleTask("runAssembleAll")
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
    result.output.contains("runAssembler ATGStorefront.ear -m DPS")
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
    result.output.contains("runAssembler ATGStorefront.ear -m DPS DSS DCS")
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
    result.output.contains("runAssembler ATGStorefront.ear -layer prod -m DPS DSS DCS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

  def "execute runAssembleStorefront task with all valid options"() {
    given:
    buildFile << """
    runAssembler {
      storefront {
        outputFileName = "ATGStorefront.ear"
        modules = ["DPS", "DSS", "DCS"]
        layers = ["prod"]
        standalone = true
        overwrite = true
        collapseClassPath = true
        collapseExcludeDirs = ["dir1","dir2"]
        collapseExcludeFiles = ["file1","file2"]
        jardirs = true
        verbose = true
        classesOnly = true
        liveconfig = true
        distributable = true
        excludeAccResources = true
        nofix = true
        tomcat = true
        tomcatUseJotm = true
        tomcatUseAtomikos = true
        jboss = true
        help = true
        usage = true
        displayName = "MyStorefront"
        server = "MyServer"
        addEarFile = ["someearfile.ear","asecondfile.ear"]
        contextRootsFile = "context.roots"
        dynamoEnvProperties = "dynamo.env"
        prependJars = ["jar1","jar2","jar3"]
        tomcatAdditionalResourcesFile = "tomcat.resources"
        tomcatInitialResourcesFile = "initial.resources"
      }
    }
"""
    when:
    def result = runGradleTask("runAssembleStorefront")
    then:
    result.output.contains("runAssembler -standalone -overwrite -collapse-class-path -collapse-exclude-dirs dir1,dir2"
        +" -collapse-exclude-files file1,file2 -jardirs -verbose -classesonly -liveconfig -distributable -exclude-acc-resources"
        +" -nofix -tomcat -tomcat-use-jotm -tomcat-use-atomikos -jboss -help -usage"
        +" -displayname MyStorefront -server MyServer -add-ear-file someearfile.ear -add-ear-file asecondfile.ear"
        +" -context-roots-file context.roots"
        +" -dynamo-env-properties dynamo.env -tomcat-additional-resources-file tomcat.resources"
        +" -tomcat-initial-resources-file initial.resources -prependJars jar1,jar2,jar3"
        +" ATGStorefront.ear -layer prod -m DPS DSS DCS")
    result.task(":runAssembleStorefront").outcome == SUCCESS
  }

}