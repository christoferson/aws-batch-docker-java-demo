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


## XML Deserializer

Add xjc to generate classes from xsd.
XSD by default is saved in src/main/xsd
Bindings are saved in src/main xjb

```
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>jaxb2-maven-plugin</artifactId>
				<version>3.1.0</version>
				<executions>
					<execution>
						<goals>
							<goal>xjc</goal>
						</goals>
					</execution>
				</executions>
				<configuration>
                   <!--<packageName>com.example.myschema</packageName>-->
                   <noGeneratedHeaderComments>true</noGeneratedHeaderComments>
                </configuration>
			</plugin>
```
			

## Links

- [xjc](https://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.2/example_xjc_basic.html)

- https://github.com/highsource/jaxb2-basics


