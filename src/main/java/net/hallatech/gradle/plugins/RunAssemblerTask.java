package net.hallatech.gradle.plugins;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public class RunAssemblerTask extends DefaultTask {
  private final Property<String> outputFileName;

  public RunAssemblerTask() {
    outputFileName = getProject().getObjects().property(String.class);
  }

  @Input
  public Property<String> getOutputFileName() {
    return outputFileName;
  }

  @TaskAction
  public void runAssembler() {
    System.out.println("Assembling " + outputFileName.get());
  }
}
