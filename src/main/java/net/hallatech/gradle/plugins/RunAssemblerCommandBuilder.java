package net.hallatech.gradle.plugins;

import java.util.ArrayList;
import java.util.List;

public class RunAssemblerCommandBuilder {

  public static final List<String> buildAssemblyFromConfiguration(RunAssemblerTask task) {

    List<String> commandList = new ArrayList<>();

    commandList.add("runAssembler");

    if (task.isPack().get()) commandList.add(" -pack");
    if (task.isStandalone().get()) commandList.add(" -standalone");
    if (task.isOverwrite().get()) commandList.add(" -overwrite");
    if (task.isCollapseClassPath().get()) commandList.add(" -collapse-class-path");

    if (task.getCollapseExcludeDirs().isPresent()) {
      commandList.add(" -collapse-exclude-dirs");
      commandList.add(" ");
      List<String> dirs = task.getCollapseExcludeDirs().get();
      commandList.add(dirs.get(0));
      dirs.subList(1,dirs.size()).forEach( dir -> {
        commandList.add(",");
        commandList.add(dir);
      });
    }

    if (task.getCollapseExcludeFiles().isPresent()) {
      commandList.add(" -collapse-exclude-files");
      commandList.add(" ");
      List<String> files = task.getCollapseExcludeFiles().get();
      commandList.add(files.get(0));
      files.subList(1,files.size()).forEach( file -> {
        commandList.add(",");
        commandList.add(file);
      });
    }

    if (task.isJardirs().get()) commandList.add(" -jardirs");
    if (task.isVerbose().get()) commandList.add(" -verbose");
    if (task.isClassesOnly().get()) commandList.add(" -classesonly");
    if (task.isLiveconfig().get()) commandList.add(" -liveconfig");
    if (task.isDistributable().get()) commandList.add(" -distributable");
    if (task.isExcludeAccResources().get()) commandList.add(" -exclude-acc-resources");
    if (task.isNofix().get()) commandList.add(" -nofix");
    if (task.isRunInPlace().get()) commandList.add(" -run-in-place");
    if (task.isTomcat().get()) commandList.add(" -tomcat");
    if (task.isTomcatUseJotm().get()) commandList.add(" -tomcat-use-jotm");
    if (task.isTomcatUseAtomikos().get()) commandList.add(" -tomcat-use-atomikos");
    if (task.isJboss().get()) commandList.add(" -jboss");
    if (task.isHelp().get()) commandList.add(" -help");
    if (task.isUsage().get()) commandList.add(" -usage");

    if (task.getDisplayName().isPresent()) {
      commandList.add(" -displayname ");
      commandList.add(task.getDisplayName().get());
    }
    if (task.getServer().isPresent()) {
      commandList.add(" -server ");
      commandList.add(task.getServer().get());
    }
    if (task.getAddEarFile().isPresent()) {
      task.getAddEarFile().get().forEach( ear -> {
        commandList.add(" -add-ear-file ");
        commandList.add(ear);
      });
    }
    if (task.getContextRootsFile().isPresent()) {
      commandList.add(" -context-roots-file ");
      commandList.add(task.getContextRootsFile().get());
    }
    if (task.getDynamoEnvProperties().isPresent()) {
      commandList.add(" -dynamo-env-properties ");
      commandList.add(task.getDynamoEnvProperties().get());
    }
    if (task.getTomcatAdditionalResourcesFile().isPresent()) {
      commandList.add(" -tomcat-additional-resources-file ");
      commandList.add(task.getTomcatAdditionalResourcesFile().get());
    }
    if (task.getTomcatInitialResourcesFile().isPresent()) {
      commandList.add(" -tomcat-initial-resources-file ");
      commandList.add(task.getTomcatInitialResourcesFile().get());
    }

    if (task.getPrependJars().isPresent()) {
      commandList.add(" -prependJars");
      commandList.add(" ");
      List<String> jars = task.getPrependJars().get();
      commandList.add(jars.get(0));
      jars.subList(1,jars.size()).forEach( jar -> {
        commandList.add(",");
        commandList.add(jar);
      });
    }

    commandList.add(" ");
    commandList.add(task.getOutputFileName().get());

    if (task.getLayers().isPresent()) {
      commandList.add(" ");
      commandList.add("-layer");
      task.getLayers().get().forEach(layer -> {
        commandList.add(" ");
        commandList.add(layer);
      });
    }

    if (task.getModules().isPresent()) {
      commandList.add(" ");
      commandList.add("-m");
      task.getModules().get().forEach(module -> {
        commandList.add(" ");
        commandList.add(module);
      });
    }

    return commandList;
  }
}
