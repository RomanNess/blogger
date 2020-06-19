IMAGE_ID?=blogger:0.1
ORGANIZATION_NAME?=blogger

all-run: build package run
all-push-to-otc: build package tag-for-otc push-to-otc

build:
	mvn clean verify

package:
	docker build -t $(IMAGE_ID) .

run:
	docker run -e POSTGRES_HOST=host.docker.internal -p 8080:8080 $(IMAGE_ID)

tag-for-otc:
	docker tag $(IMAGE_ID) swr.eu-de.otc.t-systems.com/$(ORGANIZATION_NAME)/$(IMAGE_ID)

# generate a login command in the console before pushing
push-to-otc:
	docker push swr.eu-de.otc.t-systems.com/$(ORGANIZATION_NAME)/$(IMAGE_ID)