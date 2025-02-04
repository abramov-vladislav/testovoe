### pom.xml зависимости:

```
источник: https://java-online.ru/maven-inheritance.xhtml
```
1. зависимости родительского pom.xml:

```
   <packaging>pom</packaging> 

    <modules>
        <module>task-service</module> 
        <module>user-service</module>
    </modules>
    
    <dependencyManagement>
    ...
    </dependencyManagement>
```

2. зависимости модулей pom.xml (наследование):

```
    <parent>
        <groupId>org.abramov.spring.testovoe</groupId>
        <artifactId>testovoe</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
```
3. подключение maven-зависимостей в родительский pom.xml из maven-repository:
    * lombok;
    * postgreSQL;
    * jdbc;
    * h2;
    * data jpa;
    * flyway;
    * springdoc-openai-starter-webmvc-ui; (swagger)
    * kafka (spring-boot-starter-kafka + для тестирования spring-kafka-test);