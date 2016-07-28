# Forge rest service starter

## Included

The configuration processor is included, to add property information to the packaged jar.

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

## Properties

// TODO

## Components

### Interceptor Loader

Injects all instances of ```HandlerInterceptor``` in the WebMvcConfigurerAdapter.

* Disable: ```forge.interceptor-loader.enable : false```

### Rest template

// TODO

### Spring-Actuator

**Configure cross-domain (CORS) support**
```
endpoints.cors.allowed-origins=http://example.com
endpoints.cors.allowed-methods=GET,POST
```

**Change an endpoint path**
```
# -- Changes health path to 'status' instead of 'health'
endpoints.health.path: /status
```

**Change the context path (default is '/')**
```
# -- Sets a context path for actuator
management.contextPath: /admin
```

**Change the port for actuator to listen on**
_Note_: default is the same in ```server.port```).
```
# -- Sets a different port for management
#management.port: 8081
```

**Disable actuator**
```
endpoints.enabled: false
```

**Set 'info' endpoint content**

Use `info.*` to add properties to the endpoint response. The resource filtering uses `'@\<key\>@'` instead of `'${\<key\>}'`.
```
info:
  app : @project.artifactId@
  version: @project.version@
```