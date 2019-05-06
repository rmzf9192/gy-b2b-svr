#!/usr/bin/env bash

gradle -b dist-war.gradle clean compileJava

tar cfz /tmp/srm-svr-classes.tgz -C build/classes/java/main com

scp /tmp/srm-svr-classes.tgz root@192.168.0.74:/opt/eldevops/srm-svr_dist/
