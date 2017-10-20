

#log
```xml
        <!-- log start -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.16</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>log4j-over-slf4j</artifactId>
            <version>1.7.12</version>
        </dependency>
        <!--log end -->
```


mvn deploy:deploy-file -Dfile=pom.xml -DgroupId=com.atjl -DartifactId=common-tools-parent -Dversion=1.2.0 -Dpackaging=pom -DrepositoryId=releases -Durl=http://nexus..com/nexus/content/repositories/releases/  


mvn deploy:deploy-file -Dfile=common-parent/pom.xml -DgroupId=com.atjl -DartifactId=common-parent -Dversion=1.1.0 -Dpackaging=pom -DrepositoryId=releases -Durl=http://nexus..com/nexus/content/repositories/releases/