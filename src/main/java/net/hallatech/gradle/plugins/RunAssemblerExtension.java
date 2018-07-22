package net.hallatech.gradle.plugins;

import java.util.List;

public class RunAssemblerExtension {

  private final String name;
  private String outputFileName;
  private List<String> modules;
  private List<String> layers;

  private boolean pack;
  private boolean standalone;
  private boolean overwrite;
  private boolean collapseClassPath;
  private List<String> collapseExcludeDirs;
  private List<String> collapseExcludeFiles;
  private boolean jardirs;
  private boolean verbose;
  private boolean classesOnly;
  private boolean liveconfig;
  private boolean distributable;
  private boolean excludeAccResources;
  private boolean nofix;
  private boolean runInPlace;
  private boolean tomcat;
  private boolean tomcatUseJotm;
  private boolean tomcatUseAtomikos;
  private boolean jboss;
  private boolean help;
  private boolean usage;

  private String displayName;
  private String server;
  private List<String> addEarFile;
  private String contextRootsFile;
  private String dynamoEnvProperties;
  private List<String> prependJars;
  private String tomcatAdditionalResourcesFile;
  private String tomcatInitialResourcesFile;

  private String targetBuildSubDir = "atg";

  public RunAssemblerExtension(String name) { this.name = name; }

  public String getName() { return name; }

  public void setOutputFileName(String outputFileName) { this.outputFileName = outputFileName; }

  public String getOutputFileName() { return outputFileName; }

  public List<String> getModules() { return modules; }

  public void setModules(List<String> modules) { this.modules = modules; }

  public List<String> getLayers() { return layers; }

  public void setLayers(List<String> layers) { this.layers = layers; }

  public boolean isPack() { return pack; }

  public void setPack(boolean pack) { this.pack = pack; }

  public boolean isStandalone() { return standalone; }

  public void setStandalone(boolean standalone) { this.standalone = standalone; }

  public boolean isOverwrite() { return overwrite; }

  public void setOverwrite(boolean overwrite) { this.overwrite = overwrite; }

  public boolean isCollapseClassPath() { return collapseClassPath; }

  public void setCollapseClassPath(boolean collapseClassPath) { this.collapseClassPath = collapseClassPath; }

  public List<String> getCollapseExcludeDirs() { return collapseExcludeDirs; }

  public void setCollapseExcludeDirs(List<String> collapseExcludeDirs) { this.collapseExcludeDirs = collapseExcludeDirs; }

  public List<String> getCollapseExcludeFiles() { return collapseExcludeFiles; }

  public void setCollapseExcludeFiles(List<String> collapseExcludeFiles) { this.collapseExcludeFiles = collapseExcludeFiles;}

  public boolean isJardirs() { return jardirs; }

  public void setJardirs(boolean jardirs) {
    this.jardirs = jardirs;
  }

  public boolean isVerbose() {
    return verbose;
  }

  public void setVerbose(boolean verbose) {
    this.verbose = verbose;
  }

  public boolean isClassesOnly() {
    return classesOnly;
  }

  public void setClassesOnly(boolean classesOnly) {
    this.classesOnly = classesOnly;
  }

  public boolean isLiveconfig() {
    return liveconfig;
  }

  public void setLiveconfig(boolean liveconfig) {
    this.liveconfig = liveconfig;
  }

  public boolean isDistributable() {
    return distributable;
  }

  public void setDistributable(boolean distributable) {
    this.distributable = distributable;
  }

  public boolean isExcludeAccResources() {
    return excludeAccResources;
  }

  public void setExcludeAccResources(boolean excludeAccResources) {
    this.excludeAccResources = excludeAccResources;
  }

  public boolean isNofix() {
    return nofix;
  }

  public void setNofix(boolean nofix) {
    this.nofix = nofix;
  }

  public boolean isRunInPlace() {
    return runInPlace;
  }

  public void setRunInPlace(boolean runInPlace) {
    this.runInPlace = runInPlace;
  }

  public boolean isTomcat() {
    return tomcat;
  }

  public void setTomcat(boolean tomcat) { this.tomcat = tomcat; }

  public boolean isTomcatUseJotm() {
    return tomcatUseJotm;
  }

  public void setTomcatUseJotm(boolean tomcatUseJotm) {
    this.tomcatUseJotm = tomcatUseJotm;
  }

  public boolean isTomcatUseAtomikos() {
    return tomcatUseAtomikos;
  }

  public void setTomcatUseAtomikos(boolean tomcatUseAtomikos) {
    this.tomcatUseAtomikos = tomcatUseAtomikos;
  }

  public boolean isJboss() {
    return jboss;
  }

  public void setJboss(boolean jboss) {
    this.jboss = jboss;
  }

  public boolean isHelp() {
    return help;
  }

  public void setHelp(boolean help) {
    this.help = help;
  }

  public boolean isUsage() {
    return usage;
  }

  public void setUsage(boolean usage) { this.usage = usage; }

  public String getDisplayName() { return displayName; }

  public void setDisplayName(String displayName) { this.displayName = displayName; }

  public String getServer() { return server; }

  public void setServer(String server) { this.server = server; }

  public List<String> getAddEarFile() { return addEarFile; }

  public void setAddEarFile(List<String> addEarFile) { this.addEarFile = addEarFile; }

  public String getContextRootsFile() { return contextRootsFile; }

  public void setContextRootsFile(String contextRootsFile) { this.contextRootsFile = contextRootsFile; }

  public String getDynamoEnvProperties() { return dynamoEnvProperties; }

  public void setDynamoEnvProperties(String dynamoEnvProperties) { this.dynamoEnvProperties = dynamoEnvProperties; }

  public List<String> getPrependJars() { return prependJars; }

  public void setPrependJars(List<String> prependJars) { this.prependJars = prependJars; }

  public String getTomcatAdditionalResourcesFile() { return tomcatAdditionalResourcesFile; }

  public void setTomcatAdditionalResourcesFile(String tomcatAdditionalResourcesFile) {
    this.tomcatAdditionalResourcesFile = tomcatAdditionalResourcesFile;
  }

  public String getTomcatInitialResourcesFile() { return tomcatInitialResourcesFile; }

  public void setTomcatInitialResourcesFile(String tomcatInitialResourcesFile) {
    this.tomcatInitialResourcesFile = tomcatInitialResourcesFile;
  }

  public String getTargetBuildSubDir() { return targetBuildSubDir; }

  public void setTargetBuildSubDir(String targetBuildSubDir) { this.targetBuildSubDir = targetBuildSubDir; }

}
