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

```(xml)
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

### SMTP Mail Client - SES Provider

To use smtp client, add the following dependency to the pom.xml:

```(xml)
<!-- https://mvnrepository.com/artifact/jakarta.mail/jakarta.mail-api -->
<dependency>
    <groupId>jakarta.mail</groupId>
    <artifactId>jakarta.mail-api</artifactId>
    <version>2.1.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.eclipse.angus/jakarta.mail -->
<dependency>
    <groupId>org.eclipse.angus</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>2.0.1</version>
</dependency>
```

##### Error-1

In case you get an error saying: 

> Not provider of jakarta.mail.util.StreamProvider was found

This means that you did not include an implementation e.g. org.eclipse.angus for jakarta.mail-api



## Links

- [xjc](https://www.mojohaus.org/jaxb2-maven-plugin/Documentation/v2.2/example_xjc_basic.html)

- https://github.com/highsource/jaxb2-basics

- https://docs.docker.com/build/ci/github-actions/

- https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-maven
