package net.hallatech.gradle.plugins

import spock.lang.*

class ATGRunAssemblerWrapperPluginSpec extends Specification {

  def 'runAssembler plugin description'() {
    when:
    ATGRunAssemblerWrapperPlugin plugin = new ATGRunAssemblerWrapperPlugin()
    then:
    plugin.DESCRIPTION == "Oracle Commerce (ATG) runAssembler wrapper plugin"
  }

}