package net.hallatech.gradle.plugins;

import org.gradle.api.*;
import org.gradle.api.logging.LogLevel;

public class RunAssemblerPlugin implements Plugin<Project> {

  public final static String DESCRIPTION = "Oracle Commerce (ATG) runAssembler wrapper plugin";
  public final static String EXECUTABLE_NAME = "runAssembler";
  public static final String ATG_GROUP = "ATG";
  public static final String TASK_DESCRIPTION_PREFIX = "Executes ATG runAssembler for the ";
  public static final String TASK_ALL_DESCRIPTION = "Executes ATG runAssembler for all assembly configurations ";
  public static final String TASK_PREFIX = "runAssemble";
  public static final String TASK_SUFFIX = " EAR";
  public static final String TASK_ASSEMBLE_ALL = "runAssembleAll";

  @Override
  public void apply(Project project) {
    if (project.getLogger().isEnabled(LogLevel.INFO)) {
      project.getLogger().info("Applying " +DESCRIPTION);
    }

    NamedDomainObjectContainer<RunAssemblerExtension> runAssemblerExtensionContainer =
        project.container(RunAssemblerExtension.class);
    project.getExtensions().add(EXECUTABLE_NAME, runAssemblerExtensionContainer);

    configureAssemblerTasks(project, runAssemblerExtensionContainer);
    configureAllAssemblerTask(project);
  }

  private void configureAllAssemblerTask(Project project) {
    DefaultTask task = project.getTasks().create(TASK_ASSEMBLE_ALL, DefaultTask.class);
    task.setGroup(ATG_GROUP);
    task.setDescription(TASK_ALL_DESCRIPTION);
    project.getTasks().withType(RunAssemblerTask.class).all(t -> task.dependsOn(t));
  }

  private void configureAssemblerTasks(Project project, NamedDomainObjectContainer<RunAssemblerExtension> runAssemblerExtensionContainer) {
    runAssemblerExtensionContainer.all(new Action<RunAssemblerExtension>() {
      public void execute(RunAssemblerExtension runAssemblerExtension) {
        String assembly = runAssemblerExtension.getName();
        String capitalizedAssembly = assembly.substring(0,1).toUpperCase() + assembly.substring(1);
        String taskName = TASK_PREFIX + capitalizedAssembly;

        RunAssemblerTask runAssemblerTask = project.getTasks().create(taskName,RunAssemblerTask.class);
        runAssemblerTask.setGroup(ATG_GROUP);
        runAssemblerTask.setDescription(TASK_DESCRIPTION_PREFIX + assembly + TASK_SUFFIX);

        project.afterEvaluate(new Action<Project>() {
          public void execute(Project project) {
            runAssemblerTask.getOutputFileName().set(runAssemblerExtension.getOutputFileName());
            runAssemblerTask.getModules().set(runAssemblerExtension.getModules());
            runAssemblerTask.getLayers().set(runAssemblerExtension.getLayers());

            runAssemblerTask.isPack().set(runAssemblerExtension.isPack());
            runAssemblerTask.isStandalone().set(runAssemblerExtension.isStandalone());
            runAssemblerTask.isOverwrite().set(runAssemblerExtension.isOverwrite());
            runAssemblerTask.isCollapseClassPath().set(runAssemblerExtension.isCollapseClassPath());
            runAssemblerTask.getCollapseExcludeDirs().set(runAssemblerExtension.getCollapseExcludeDirs());
            runAssemblerTask.getCollapseExcludeFiles().set(runAssemblerExtension.getCollapseExcludeFiles());
            runAssemblerTask.isJardirs().set(runAssemblerExtension.isJardirs());
            runAssemblerTask.isVerbose().set(runAssemblerExtension.isVerbose());
            runAssemblerTask.isClassesOnly().set(runAssemblerExtension.isClassesOnly());
            runAssemblerTask.isLiveconfig().set(runAssemblerExtension.isLiveconfig());
            runAssemblerTask.isDistributable().set(runAssemblerExtension.isDistributable());
            runAssemblerTask.isExcludeAccResources().set(runAssemblerExtension.isExcludeAccResources());
            runAssemblerTask.isNofix().set(runAssemblerExtension.isNofix());
            runAssemblerTask.isRunInPlace().set(runAssemblerExtension.isRunInPlace());
            runAssemblerTask.isTomcat().set(runAssemblerExtension.isTomcat());
            runAssemblerTask.isTomcatUseJotm().set(runAssemblerExtension.isTomcatUseJotm());
            runAssemblerTask.isTomcatUseAtomikos().set(runAssemblerExtension.isTomcatUseAtomikos());
            runAssemblerTask.isJboss().set(runAssemblerExtension.isJboss());
            runAssemblerTask.isHelp().set(runAssemblerExtension.isHelp());
            runAssemblerTask.isUsage().set(runAssemblerExtension.isUsage());

            runAssemblerTask.getDisplayName().set(runAssemblerExtension.getDisplayName());
            runAssemblerTask.getServer().set(runAssemblerExtension.getServer());
            runAssemblerTask.getAddEarFile().set(runAssemblerExtension.getAddEarFile());
            runAssemblerTask.getContextRootsFile().set(runAssemblerExtension.getContextRootsFile());
            runAssemblerTask.getDynamoEnvProperties().set(runAssemblerExtension.getDynamoEnvProperties());
            runAssemblerTask.getPrependJars().set(runAssemblerExtension.getPrependJars());
            runAssemblerTask.getTomcatAdditionalResourcesFile().set(runAssemblerExtension.getTomcatAdditionalResourcesFile());
            runAssemblerTask.getTomcatInitialResourcesFile().set(runAssemblerExtension.getTomcatInitialResourcesFile());

            runAssemblerTask.getTargetBuildSubDir().set(runAssemblerExtension.getTargetBuildSubDir());

            RunAssemblerValidator.validate(runAssemblerTask);
          }
        });
      }
    });
  }
}
