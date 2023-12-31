FROM bellsoft/liberica-openjdk-alpine:17.0.8

#install curl and jq: required to fetch the output of 
#command curl -s http://localhost:4444/status | jq .value.status
#the above command will be used to check whether the grid is 
#completly up or not before starting selenium-tests container.
RUN apk add curl jq

#workspace
WORKDIR /home/selenium-docker

#Add the required project files
ADD /target/docker-resources    ./
ADD runner.sh	                runner.sh

#Start the runner.sh
ENTRYPOINT sh runner.sh