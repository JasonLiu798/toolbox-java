

#依赖
##spring
```xml
	<import resource="classpath*:spring/*.xml" />
```

##ehcache
多机之间同步依赖于ehcache失效时间
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache>
    <diskStore path="java.io.tempdir"/>
    <defaultCache maxElementsInMemory="10000" eternal="false"
                  timeToIdleSeconds="120" timeToLiveSeconds="120"
                  overflowToDisk="true"
                  diskSpoolBufferSizeMB="30" maxElementsOnDisk="10000000"
                  diskPersistent="false" diskExpiryThreadIntervalSeconds="120"
                  memoryStoreEvictionPolicy="LRU"/>
    <cache name="CONFIGCACHE" maxElementsInMemory="10000" eternal="false"
           timeToIdleSeconds="120" timeToLiveSeconds="120" overflowToDisk="true"
           diskSpoolBufferSizeMB="30" maxElementsOnDisk="10000000"
           diskPersistent="false" diskExpiryThreadIntervalSeconds="120"
           memoryStoreEvictionPolicy="LRU"/>

    <cache name="CONFIGCACHE_M" maxElementsInMemory="10000" eternal="false"
           timeToIdleSeconds="120" timeToLiveSeconds="120" overflowToDisk="true"
           diskSpoolBufferSizeMB="30" maxElementsOnDisk="10000000"
           diskPersistent="false" diskExpiryThreadIntervalSeconds="120"
           memoryStoreEvictionPolicy="LRU"/>

    <cache name="CONFIGCACHE_MK" maxElementsInMemory="10000" eternal="false"
           timeToIdleSeconds="120" timeToLiveSeconds="120" overflowToDisk="true"
           diskSpoolBufferSizeMB="30" maxElementsOnDisk="10000000"
           diskPersistent="false" diskExpiryThreadIntervalSeconds="120"
           memoryStoreEvictionPolicy="LRU"/>
</ehcache>
```

##mybatis
读库
```xml
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="mapperLocations">
            <list>
                <value>classpath*:/sqlmap/biz/*Mapper.xml</value>
            </list>
        </property>
        <property name="configLocation" value="classpath:mybatis-config.xml"></property>
    </bean>
    
    <!-- ScanMapperFiles -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.atjl.**.dao" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>
``` 
    
    

