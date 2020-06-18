all-run: build package run

build:
	mvn clean verify

package:
	docker build -t blogger:0.1 .

run:
	docker run -e POSTGRES_HOST=host.docker.internal -p 8080:8080 blogger:0.1