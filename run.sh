#!/bin/bash
if [ ! -d "hackerrank-cli/target/" ]; then
	echo "target/ dir is missing. Build app with: mvn install";
	exit 0;
fi
java -jar hackerrank-cli/target/jt.jar "$@"
