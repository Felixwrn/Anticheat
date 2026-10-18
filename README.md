# AdvancedAntiCheat

Minecraft-Plugin für Paper 26.3 mit Java 25. Die Paper-API ist auf
`26.3.build.200-stable` festgelegt. ProtocolLib wird nicht benötigt.

Zum Bauen werden JDK 25 und Maven 3.9.11 oder neuer benötigt:

```sh
mvn --batch-mode --no-transfer-progress clean verify
```

Die Plugin-Datei `target/AdvancedAntiCheat-1.0.jar` in das `plugins`-Verzeichnis
eines Paper-26.3-Servers kopieren und den Server mit Java 25 starten.
Die bisherigen Fly-, Speed- und XRay-Prüfungen bleiben erhalten. Für dieses
Repository sind keine automatisierten Tests vorhanden; vor dem produktiven
Einsatz die Prüfungen mit Testspielern auf einem separaten Server ausprobieren.
