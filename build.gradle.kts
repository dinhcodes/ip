plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Gson for JSON serialization/deserialization
    implementation("com.google.code.gson:gson:2.10.1")

    // JUnit for testing
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("main.java.Ello")
}

tasks.test {
    useJUnitPlatform()
}
