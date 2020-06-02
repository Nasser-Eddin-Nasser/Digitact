#!/usr/bin/env bash
find src -type f -name \*.java -exec java -jar tools/google-java-format-1.6-all-deps.jar --replace --aosp {} +
