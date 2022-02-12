all:
	@mvn install
	@mv bin/Delta-jar-with-dependencies.jar bin/Delta.jar

test:
	@mvn test

package:
	@zip -r Delta.zip bin docs src README.md

.PHONY: all test
