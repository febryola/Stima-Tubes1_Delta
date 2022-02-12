all:
	@mvn install
	@mv bin/Delta-jar-with-dependencies.jar bin/Delta.jar

test:
	@mvn test

.PHONY: all test
