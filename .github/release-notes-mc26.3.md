AdvancedAntiCheat for Paper 26.3 and Java 25. The existing plugin version is `b0.1`; the Maven artifact remains `AdvancedAntiCheat-1.0.jar`. This is a prerelease.

Install `AdvancedAntiCheat-1.0.jar` in the server's `plugins` directory and restart with Java 25. ProtocolLib is not required. The existing Fly, Speed and XRay checks are retained.

The release workflow builds the tagged source with `mvn clean verify` and publishes the final JAR with `SHA256SUMS`. The repository has no automated tests. In-game behavior has not been tested; try the checks with test players on a separate server before production use.
