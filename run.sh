#!/bin/bash
if [ ! -d "solution-cli/target/" ]; then
	echo "target/ dir is missing. Build app with: mvn install";
	exit 0;
fi
java -jar solution-cli/target/jt.jar "$@"
