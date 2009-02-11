#!/bin/bash

# This line works with the gradle build
# groovy -cp build/classes src/main/groovy/org/groovy/example/Runner.groovy $@

# This line works for IntelliJ's build
groovy -cp out/production/unitConverter src/main/groovy/org/groovy/example/Runner.groovy $@
