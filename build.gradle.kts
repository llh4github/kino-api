import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    id("com.google.devtools.ksp") version "1.7.22-1.0.8"
    kotlin("kapt") version "1.7.22"
}

group = "com.github.llh4github"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
//    maven { setUrl("https://mirrors.huaweicloud.com/repository/maven") }
//    maven { setUrl("https://maven.aliyun.com/repository/spring") }
    maven { setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/") }
    mavenCentral()
}

dependencies {

    val jimmerVersion = "0.7.19"
    val saTokenVersion = "1.34.0"
    val springdocVersion = "2.0.2"
    val mapstrctVersion = "1.5.5.Final"

    // 自己开发的ksp插件，测试完成后放到maven仓库里
//    ksp(fileTree("libs", "core-0.0.1.jar"))
    implementation(fileTree(mapOf("dir" to "libs", "include" to "core-0.0.1.jar")))
    ksp(fileTree(mapOf("dir" to "libs", "include" to "ksp-0.0.1.jar")))
    ksp(fileTree(mapOf("dir" to "libs", "include" to "core-0.0.1.jar")))
    ksp("com.facebook:ktfmt:0.44")
    testImplementation("com.apifan.common:common-random:1.0.19")
    //region 安全框架
    implementation("cn.dev33:sa-token-spring-boot3-starter:${saTokenVersion}")
    implementation("cn.dev33:sa-token-dao-redis-jackson:${saTokenVersion}")
    //endregion

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${springdocVersion}")

    //region jimmer框架
    implementation("org.babyfish.jimmer:jimmer-spring-boot-starter:${jimmerVersion}")
    ksp("org.babyfish.jimmer:jimmer-ksp:${jimmerVersion}")
//    implementation("org.mapstruct:mapstruct:${mapstrctVersion}")
//    kapt("org.mapstruct:mapstruct-processor:${mapstrctVersion}")
//    kapt("org.babyfish.jimmer:jimmer-mapstruct-apt:${jimmerVersion}")
    //endregion
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.flywaydb:flyway-core")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}


kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
