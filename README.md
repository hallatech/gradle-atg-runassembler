# gradle-atg-runassembler
Gradle wrapper plugin for the _Oracle Commerce ATG runAssembler_ utility

The plugin is written in *Java*, *Groovy* and *Kotlin* as an exercise in learning and language comparison but all versions of the plugins are intended for actual use.

The plugin contains unit tests in *JUnit5(Java)*, *Spock (Groovy)*, *Spek (Kotlin)*.
It also contains functional tests using the Gradle Testkit using *JUnit4 (Java)*, *Spock (Groovy)* and vanilla *Kotlin*.

_*Note:*_ *JUnit5* was not used for functional tests due to the slightly complicated mechanism of the replacement of JUnit4 Rules with extensions and a viable solution for temporary folders in the functional tests.
This may have been overcome but it still seems a bit over-complicated for what is needed here.


# Build

The build configuration uses the Groovy DSL rather than Kotlin script - one step at a time!
The default build tasks are set to `clean build`.

*Functional tests* are split out into their own source set configuration.
    