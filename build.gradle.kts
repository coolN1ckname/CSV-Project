plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.jfree:jfreechart:1.5.3")
    implementation("org.postgresql:postgresql:42.5.0")
}

tasks.test {
    useJUnitPlatform()
}