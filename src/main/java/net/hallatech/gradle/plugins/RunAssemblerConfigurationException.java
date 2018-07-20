package net.hallatech.gradle.plugins;

import org.gradle.api.GradleException;

public class RunAssemblerConfigurationException extends GradleException {

  private static final String ERROR_PREFIX = "The runAssembler {} configuration is invalid: ";

  public RunAssemblerConfigurationException(String message) {
    throw new GradleException(ERROR_PREFIX +message);
  }
}
