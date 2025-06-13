plugins {
	java
	id("org.springframework.boot") version "3.5.0"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "net.serg"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("jakarta.jms:jakarta.jms-api:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("software.amazon.awssdk:sqs:2.31.60")
	implementation("software.amazon.awssdk:dynamodb-enhanced:2.31.60")
	implementation("software.amazon.awssdk:sts:2.31.60")
	implementation("org.springframework:spring-jms:6.2.7")
	implementation("com.amazonaws:aws-java-sdk-sts:1.12.785")
	implementation("com.amazonaws:amazon-sqs-java-messaging-lib:2.1.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
