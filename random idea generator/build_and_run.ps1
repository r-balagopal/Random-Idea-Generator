# Build and run script for Windows PowerShell
# Usage: Open PowerShell in project root and run: .\build_and_run.ps1

Set-StrictMode -Version Latest

$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Definition
Push-Location $projectRoot

Write-Host "Running mvn clean package..."
$mvnResult = & mvn clean package
if ($LASTEXITCODE -ne 0) {
    Write-Error "Maven build failed. See output above."
    Pop-Location
    exit $LASTEXITCODE
}

# Find shaded jar (artifactId-version-shaded.jar)
$shaded = Get-ChildItem -Path .\target -Filter "*-shaded.jar" -File | Sort-Object LastWriteTime -Descending | Select-Object -First 1
if (-not $shaded) {
    Write-Error "Shaded JAR not found in target/. Ensure the Shade plugin executed and produced a shaded JAR."
    Pop-Location
    exit 1
}

Write-Host "Found shaded JAR: $($shaded.Name)"
Write-Host "Running: java -jar $($shaded.FullName)"
& java -jar $shaded.FullName

Pop-Location
