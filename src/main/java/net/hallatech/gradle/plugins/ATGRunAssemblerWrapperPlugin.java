package net.hallatech.gradle.plugins;

import org.gradle.api.*;
import org.gradle.api.logging.LogLevel;

public class ATGRunAssemblerWrapperPlugin implements Plugin<Project> {

  public final static String DESCRIPTION = "Oracle Commerce (ATG) runAssembler wrapper plugin";
  public final static String OBJECT_NAME = "runAssembler";
  public static final String ATG_GROUP = "ATG";
  public static final String TASK_DESCRIPTION_PREFIX = "Executes ATG runAssembler for the ";
  public static final String TASK_ALL_DESCRIPTION = "Executes ATG runAssembler for all assembly configurations ";
  public static final String TASK_PREFIX = "assemble";
  public static final String TASK_SUFFIX = " EAR";
  public static final String TASK_ASSEMBLE_ALL = "assembleAll";

  @Override
  public void apply(Project project) {
    if (project.getLogger().isEnabled(LogLevel.INFO)) {
      project.getLogger().info("Applying " +DESCRIPTION);
    }

    NamedDomainObjectContainer<AssemblyConfiguration> assemblyConfigurationContainer =
        project.container(AssemblyConfiguration.class);
    project.getExtensions().add(OBJECT_NAME, assemblyConfigurationContainer);

    configureAssemblerTasks(project, assemblyConfigurationContainer);
    configureAllAssemblerTask(project);

  }

  private void configureAllAssemblerTask(Project project) {
    DefaultTask task = project.getTasks().create(TASK_ASSEMBLE_ALL, DefaultTask.class);
    task.setGroup(ATG_GROUP);
    task.setDescription(TASK_ALL_DESCRIPTION);
    project.getTasks().withType(RunAssemblerTask.class).all(t -> task.dependsOn(t));
  }

  private void configureAssemblerTasks(Project project, NamedDomainObjectContainer<AssemblyConfiguration> assemblyConfigurationContainer) {
    assemblyConfigurationContainer.all(new Action<AssemblyConfiguration>() {
      public void execute(AssemblyConfiguration assemblyConfiguration) {
        String assembly = assemblyConfiguration.getName();
        String capitalizedAssembly = assembly.substring(0,1).toUpperCase() + assembly.substring(1);
        String taskName = TASK_PREFIX + capitalizedAssembly;

        RunAssemblerTask runAssemblerTask = project.getTasks().create(taskName,RunAssemblerTask.class);
        runAssemblerTask.setGroup(ATG_GROUP);
        runAssemblerTask.setDescription(TASK_DESCRIPTION_PREFIX + assembly + TASK_SUFFIX);

        project.afterEvaluate(new Action<Project>() {
          public void execute(Project project) {
            runAssemblerTask.getOutputFileName().set(assemblyConfiguration.getOutputFileName());
          }
        });
      }
    });
  }
}
