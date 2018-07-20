package net.hallatech.gradle.plugins

import spock.lang.*

class RunAssemblerPluginSpec extends Specification {

  def 'runAssembler plugin description'() {
    when:
    RunAssemblerPlugin plugin = new RunAssemblerPlugin()
    then:
    plugin.DESCRIPTION == "Oracle Commerce (ATG) runAssembler wrapper plugin"
  }

}