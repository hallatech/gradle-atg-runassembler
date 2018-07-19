package net.hallatech.gradle.plugins;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;

public class AssemblyConfiguration {

  private final String name;
  private String outputFileName;

  public AssemblyConfiguration(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  public String getOutputFileName() {
    return outputFileName;
  }


}
