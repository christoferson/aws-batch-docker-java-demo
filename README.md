# aws-batch-docker-java-demo
Aws Batch Docker Java with AWS SDK Demo

## Aws Batch Docker Java Demo

### Todo

- Update ${docker-id}

### Build Application

mvn clean package

### Run Application Jar Locally

java -jar ./target/aws-batch-docker-java-demo-1.0.0.jar

### Create Image

docker build -t ${docker-id}/aws-batch-docker-java-demo .

### Run Image

docker run ${docker-id}/aws-batch-docker-java-demo

### Login to Running Container

docker exec -ti loving_knuth /bin/sh

### Push Image

docker push ${docker-id}/aws-batch-docker-java-demo


## Batch Parameters


```
{"Parameters": {"name":"test"}, "ContainerOverrides": { "Command": ["echo","Ref::name"] } }
````
