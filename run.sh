#!/bin/bash
if [ ! -d "target/" ]; then
	echo "target/ dir is missing. Build app with: mvn install";
	exit 0;
fi
java -jar target/jt.jar "$@"
