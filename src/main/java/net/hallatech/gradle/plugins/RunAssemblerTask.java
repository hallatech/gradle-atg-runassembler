package net.hallatech.gradle.plugins;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import java.util.List;
import java.util.stream.Collectors;

public class RunAssemblerTask extends DefaultTask {
  private final Property<String> outputFileName;
  private final ListProperty<String> modules;
  private final ListProperty<String> layers;

  private final Property<Boolean> pack;
  private final Property<Boolean> standalone;
  private final Property<Boolean> overwrite;
  private final Property<Boolean> collapseClassPath;
  private final ListProperty<String> collapseExcludeDirs;
  private final ListProperty<String> collapseExcludeFiles;
  private final Property<Boolean> jardirs;
  private final Property<Boolean> verbose;
  private final Property<Boolean> classesOnly;
  private final Property<Boolean> liveconfig;
  private final Property<Boolean> distributable;
  private final Property<Boolean> excludeAccResources;
  private final Property<Boolean> nofix;
  private final Property<Boolean> runInPlace;
  private final Property<Boolean> tomcat;
  private final Property<Boolean> tomcatUseJotm;
  private final Property<Boolean> tomcatUseAtomikos;
  private final Property<Boolean> jboss;
  private final Property<Boolean> help;
  private final Property<Boolean> usage;

  private final Property<String> displayName;
  private final Property<String> server;
  private final ListProperty<String> addEarFile;
  private final Property<String> contextRootsFile;
  private final Property<String> dynamoEnvProperties;
  private final ListProperty<String> prependJars;
  private final Property<String> tomcatAdditionalResourcesFile;
  private final Property<String> tomcatInitialResourcesFile;


  public RunAssemblerTask() {
    outputFileName = getProject().getObjects().property(String.class);
    modules = getProject().getObjects().listProperty(String.class);
    layers = getProject().getObjects().listProperty(String.class);
    pack = getProject().getObjects().property(Boolean.class);
    standalone = getProject().getObjects().property(Boolean.class);
    overwrite = getProject().getObjects().property(Boolean.class);
    collapseClassPath = getProject().getObjects().property(Boolean.class);
    collapseExcludeDirs = getProject().getObjects().listProperty(String.class);
    collapseExcludeFiles = getProject().getObjects().listProperty(String.class);
    jardirs = getProject().getObjects().property(Boolean.class);
    verbose = getProject().getObjects().property(Boolean.class);
    classesOnly = getProject().getObjects().property(Boolean.class);
    liveconfig = getProject().getObjects().property(Boolean.class);
    distributable = getProject().getObjects().property(Boolean.class);
    excludeAccResources = getProject().getObjects().property(Boolean.class);
    nofix = getProject().getObjects().property(Boolean.class);
    runInPlace = getProject().getObjects().property(Boolean.class);
    tomcat = getProject().getObjects().property(Boolean.class);
    tomcatUseJotm = getProject().getObjects().property(Boolean.class);
    tomcatUseAtomikos = getProject().getObjects().property(Boolean.class);
    jboss = getProject().getObjects().property(Boolean.class);
    help = getProject().getObjects().property(Boolean.class);
    usage = getProject().getObjects().property(Boolean.class);
    displayName = getProject().getObjects().property(String.class);
    server = getProject().getObjects().property(String.class);
    addEarFile = getProject().getObjects().listProperty(String.class);
    contextRootsFile = getProject().getObjects().property(String.class);
    dynamoEnvProperties = getProject().getObjects().property(String.class);
    prependJars = getProject().getObjects().listProperty(String.class);
    tomcatAdditionalResourcesFile = getProject().getObjects().property(String.class);
    tomcatInitialResourcesFile = getProject().getObjects().property(String.class);
  }

  @Input
  public Property<String> getOutputFileName() { return outputFileName; }

  @Input
  public ListProperty<String> getModules() { return modules; }

  public ListProperty<String> getLayers() { return layers; }

  public Property<Boolean> isPack() { return pack; }
  
  public Property<Boolean> isStandalone() { return standalone; }

  public Property<Boolean> isOverwrite() { return overwrite; }

  public Property<Boolean> isCollapseClassPath() { return collapseClassPath; }

  public ListProperty<String> getCollapseExcludeDirs() { return collapseExcludeDirs; }

  public ListProperty<String> getCollapseExcludeFiles() { return collapseExcludeFiles; }

  public Property<Boolean> isJardirs() { return jardirs; }

  public Property<Boolean> isVerbose() { return verbose; }

  public Property<Boolean> isClassesOnly() { return classesOnly; }

  public Property<Boolean> isLiveconfig() { return liveconfig; }

  public Property<Boolean> isDistributable() { return distributable; }

  public Property<Boolean> isExcludeAccResources() { return excludeAccResources; }

  public Property<Boolean> isNofix() { return nofix; }

  public Property<Boolean> isRunInPlace() { return runInPlace; }

  public Property<Boolean> isTomcat() { return tomcat; }

  public Property<Boolean> isTomcatUseJotm() { return tomcatUseJotm; }

  public Property<Boolean> isTomcatUseAtomikos() { return tomcatUseAtomikos; }

  public Property<Boolean> isJboss() { return jboss; }

  public Property<Boolean> isHelp() { return help; }

  public Property<Boolean> isUsage() { return usage; }

  public Property<String> getDisplayName() { return displayName; }

  public Property<String> getServer() { return server; }

  public ListProperty<String> getAddEarFile() { return addEarFile; }

  public Property<String> getContextRootsFile() { return contextRootsFile; }

  public Property<String> getDynamoEnvProperties() { return dynamoEnvProperties; }

  public ListProperty<String> getPrependJars() { return prependJars; }

  public Property<String> getTomcatInitialResourcesFile() { return tomcatAdditionalResourcesFile; }

  public Property<String> getTomcatAdditionalResourcesFile() { return tomcatInitialResourcesFile; }


  @TaskAction
  public void runAssembler() {
    System.out.println("Assembling " + outputFileName.get());

    List<String> assemblyCommandList = RunAssemblerCommandBuilder.buildAssemblyFromConfiguration(this);

    System.out.println(assemblyCommandList.stream().collect(Collectors.joining()));

  }
}
