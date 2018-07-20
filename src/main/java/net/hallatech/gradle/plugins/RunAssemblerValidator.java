package net.hallatech.gradle.plugins;

public class RunAssemblerValidator {


  public static final void validate(RunAssemblerTask task) {
    if (task.isCollapseClassPath().get() && task.isRunInPlace().get()) {
      throw new RunAssemblerConfigurationException("collapseClassPath and runInPlace cannot be used together.");
    }

    if (task.getCollapseExcludeDirs().isPresent() && !task.isCollapseClassPath().get()) {
      throw new RunAssemblerConfigurationException("collapseClassPath is required if getCollapseExcludeDirs is set.");
    }

    if (task.getCollapseExcludeFiles().isPresent() && !task.isCollapseClassPath().get()) {
      throw new RunAssemblerConfigurationException("collapseClassPath is required if getCollapseExcludeFiles is set.");
    }

    if (task.isRunInPlace().get() && !task.isJboss().get()) {
      throw new RunAssemblerConfigurationException("runInPlace cannot be used without jboss.");
    }

  }
}
