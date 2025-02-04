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
```

2. зависимости модулей pom.xml (наследование):

```
    <parent>
        <groupId>org.abramov.spring.testovoe</groupId>
        <artifactId>testovoe</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
```
