# Idea Generator (Java)

This project is a small idea manager UI. The Maven build produces an executable shaded JAR containing dependencies (including `org.json`).

Quick commands (PowerShell):

1. Build and run using the helper script:

```powershell
cd 'C:\Users\LENOVO\OneDrive\Documents\random idea generator'
.\build_and_run.ps1
```

2. Or run Maven and then the shaded jar manually:

```powershell
mvn clean package
# the shaded jar will be something like target\idea-generator-1.0.0-shaded.jar
java -jar .\target\idea-generator-1.0.0-shaded.jar
```

Notes
- If your main class is in a package, update the `<mainClass>` entries in `pom.xml` (both `exec-maven-plugin` and `maven-shade-plugin`) to the fully-qualified class name (e.g., `com.example.IdeaGeneratorUI`).
- The shaded JAR will be named using the pattern `<artifactId>-<version>-shaded.jar` and placed in `target/`.
- `pom.xml` is configured to enable a dependency-reduced POM (`createDependencyReducedPom=true`) to prevent duplicate dependencies in the shaded jar's POM.

If you want a different shaded JAR filename, tell me the exact name you'd like and I will update `pom.xml` accordingly.

Package & Source Layout
- The project sources have been moved into a Java package: `com.example`.
- Source files are located under the Maven standard layout:
	- `src/main/java/com/example/IdeaGeneratorUI.java`
	- `src/main/java/com/example/IdeaManager.java`
	- `src/main/java/com/example/Idea.java`
	- `src/main/java/com/example/AddIdeaDialog.java`
	- `src/main/java/com/example/EditIdeaDialog.java`

Build & Run Notes
- The `pom.xml` is configured with `<mainClass>com.example.IdeaGeneratorUI</mainClass>` for both the `exec-maven-plugin` and the Shade plugin. If you change the package or move the main class, update those entries.
- To build and run (PowerShell):

```powershell
cd 'C:\Users\LENOVO\OneDrive\Documents\random idea generator'
# build and create shaded jar
mvn clean package

# run the shaded jar
java -jar .\target\idea-generator-1.0.0-shaded.jar
```

Helper script
- Use `build_and_run.ps1` to run the build and execute the shaded JAR automatically.

Cleaning up old root sources
- There are older `.java` files in the project root (from earlier iterations). Maven uses `src/main/java/` so those root files are ignored by the Maven build. You can delete them to avoid confusion; say the word and I'll remove them.

