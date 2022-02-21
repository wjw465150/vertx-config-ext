# vertx-config-ext
load vertx config file from classpath!

## 依赖引入

### 通过Maven的`pom.xml`引入。

```xml
  ......

	<dependencies>
		<dependency>
			<groupId>io.vertx</groupId>
			<artifactId>vertx-core</artifactId>
      <version>4.2.5</version>
		</dependency>

		<dependency>
			<groupId>io.vertx</groupId>
			<artifactId>vertx-config</artifactId>
      <version>4.2.5</version>
		</dependency>

    <dependency>
      <groupId>com.github.wjw465150</groupId>
      <artifactId>vertx-config-ext</artifactId>
      <version>4.2.5</version>
    </dependency>
	</dependencies>

  ......
```



### 或者通过Gradle的`build.gradle`引入

```groovy
dependencies {
  implementation "io.vertx:vertx-core:4.2.5"
  implementation "io.vertx:vertx-config:4.2.5"
  implementation "com.github.wjw465150:vertx-config-ext:4.2.5"
}

```

## 使用示例

```java
  ConfigStoreOptions classpathStore = new ConfigStoreOptions()
      .setType("classpath")  //<1>
      .setConfig(new JsonObject().put("path", "conf/conf-test.json")); //<2>
    

  ConfigRetrieverOptions configOptions = new ConfigRetrieverOptions().addStore(classpathStore);
  ConfigRetriever retriever = ConfigRetriever.create(vertx,configOptions);

```

> <1> setType的值必须是`classpath`
>
> <2> key的值必须是`path`,value值是配置文件在classpath里的绝对路径,注意千万不要以`/`开头!

