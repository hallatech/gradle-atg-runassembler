# gradle-atg-runassembler
Gradle wrapper plugin for the _Oracle Commerce ATG runAssembler_ utility

This is an update to the original one written for Spindrift: https://plugins.gradle.org/plugin/com.spindrift.run-assembler
which is still available and operational.

Development features of this version:
- uses the latest versions of Gradle (initially 4.9)
- written in Java 8 using more up to date language feature, e.g. lambdas
- pure Java for the plugin for performance improvements and static typing) 
- uses Spock (Groovy) for the testing
- uses Gradle TestKit for functional testing
- removes any Gradle deprecations ready for Gradle 5
- implements latest recommendations for plugin development
- does not use any 3rd party transitive dependencies that could interfere with consuming builds

The Gradle `tasks` output will look something like this:

    ATG tasks
    ---------
    runAssembleAll - Executes ATG runAssembler for all assembly configurations 
    runAssembleBcc - Executes ATG runAssembler for the bcc EAR
    runAssembleCsc - Executes ATG runAssembler for the csc EAR
    runAssembleLockserver - Executes ATG runAssembler for the lockserver EAR
    runAssembleStorefront - Executes ATG runAssembler for the storefront EAR
    
# Execution pre-requisites
To execute the tasks a local Oracle Commerce (ATG) installation is required. For this plugin the minimum version required is `11.3`, although it should work with previous versions supported on JDK8.  
The runAssembler utility that this plugin wraps is found at `$ATG_HOME/home/bin/runAssembler|.bat`.
The ATG_HOME environment variable must also be set.

# Plugin tasks

- A task is generated for each named assembly configuration
- A wrapper `assembleAll` task is generated that `dependsOn` all others.
- A `runAssemblyConfigurations` task will display the details of the final configurations.

# Validation
There are a few simple validation rules applied that match some of the option combinations allowed. If these are violated an exception is thrown with an error message.

# Applying the plugin

To add to your build using the new plugin configuration:

    plugins {
      id "net.hallatech.atg-runassembler" version("0.1")
    }
    
To use the older format:

    buildscript {
      repositories {
        ...
      }
      dependencies {
        classpath group: "net.hallatech.atg-runassembler", name: "net.hallatech.atg-runassembler.gradle.plugin", version: "0.1"
      }
    }
    
    apply plugin: "net.hallatech.atg-runassembler"
   
To configure the plugin use the following DSL block:

    runAssembler {
      ...
    }
    
Detailed configuration in the next section below.

# Configuration

Each EAR has its own assembly configuration closure within the `runAssembler` closure. Configure a name for the assembly that will be capitalized and prefixed with `runAssemble` to create a dynamic task.

    runAssembler {
        storefront { }
    }
    
will create `runAssembleStorefront` 

The `runAssembler` utility has the following usage:

    runAssembler [option*] output-file-name [-layer config-layer-list] -m dynamo-module-list
    
which means the only required configuration is the `output-file-name` of the target EAR and at least one valid module.

Options, modules and layers are configured as follows:

     runAssembler {
         storefront {
            jboss = true
            standalone = true
            prependJars = ["jar1","jar2"]
            modules = ["DCS","MyBaseModule","MyCommerceModule"]
            layers = ["prod","acceptance","integration","test","ci"]
         }
     }

# Custom Task Types
  RunAssembler - Executes the runAssembler utility
  
# Extension Properties

Assembly properties:

Property	| Type	| Default Value	| Matched runAssembler parameter  
--------  | ----  | ------------- | ------------------------------
outputFileName*	| String	| none	| -output-file-name  
modules*	| List	| none |	-m 
layers	| List	| none	| -layer  

Options appear at the same level as modules and layers - they don't have their own closure block.  

Property	| Type	| Default Value	| Matched runAssembler parameter  
--------  | ----  | ------------- | ------------------------------
pack | boolean | false | -pack
standalone | boolean | false | -standalone
overwrite | boolean | false | -overwrite
collapseClassPath | boolean| false | -collapse-class-path
collapseExcludeDirs | List<String>  | [] | -collapse-exclude-dirs
collapseExcludeFiles | List<String> | [] | -collapse-exclude-files
jardirs | boolean | false | -jardirs
verbose | boolean | false | -verbose
classesOnly | boolean | false | -classesonly
displayName | String | '' | -displayname <name>
serverName | String | '' | -server
liveconfig | boolean | false | -liveconfig
distributable | boolean | false | -distributable
addEarFile | List<String> | [] | -add-ear-file <EAR file name>
contextRootsFile | String | '' | -context-roots-file <properties file>
dynamoEnvProperties | String | '' | -dynamo-env-properties <properties file>
excludeAccResources | boolean | false | -exclude-acc-resources
noFix | boolean | false | -nofix
prependJars | List<String> | [] | -prependJars <jar1,jar2,...>
runInPlace | boolean | false | -run-in-place
tomcat | boolean | false | -tomcat
tomcatAdditionalResourcesFile | String | false | -tomcat-additional-resources-file <fileName>
tomcatInitialResourcesFile | String | false | -tomcat-initial-resources-file <fileName>
tomcatUseJotm | boolean | false | -tomcat-use-jotm
tomcatUseAtomikos | boolean | false | -tomcat-use-atomikos
jboss | boolean | false | -jboss
help | boolean | false | -help
usage | boolean | false | -usage
 
# Build

The default build tasks are set to `publishtoMavenLocal` and includes functional tests.

# Functional Testing
*Functional tests* are split out into their own source set configuration.
They are implemented using the Gradle TestKit (with Spock).  
*Note* These tests run the actual runAssembler utility so the full scale of all the options is not possible. In addition a valid ATG installation is required. See pre-requisites above.


# Acceptance Testing
A separate non-subproject `acceptance` module uses the plugin in its own gradle build. 

It uses the version of the plugin that is in the local Maven repository when the root project executes the `publishtoMavenLocal` task.  

*Note:* It does not use the `plugins {}` block as it expects to be testing the local development version rather than the publicly published one.  

It doesn't have its own automated tests but its just for running via the command line. There is a wrapper tasks `acceptanceTests` which calls the main or wrapper tasks of the plugin. This is configured for the defaultTasks,

Simply run `gradle` or `./gradlew` to execute all the tasks of the plugin.

# Publishing
The default task is build plus a local maven install so that the functional tests can immediately be executed.

To publish to the gradle plugin portal:

- Ensure versions are correct
- Ensure README notes versions match publishing versions
- Ensure functional and acceptance test versions matches publishing versions
- Run `gradle clean build publishPlugins -Prelease=true` to publish to the gradle plugin portal. If you don't add -Prelease=true a SNAPSHOT version will be released

    