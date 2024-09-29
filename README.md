# spring-boot-starter-monitor

This project demonstrates how to integrate OpenTelemetry with a Spring Boot application to collect and send logs, traces, and metrics to Elasticsearch, Kibana, and Elastic APM. The project consists of:

    Weather Gateway: Handles incoming requests and routes them to the weather aggregator service.
    Weather Aggregator: Aggregate weather information from third party weather API and return consolidated response.
    Third-Party API: Provides weather information.
    
<a href="https://github.com/sats17/spring-boot-microservice-starter-monitor">
<img align="left" height="100px" src="https://github.com/sats17/spring-boot-microservice-starter-monitor/blob/main/files/app-info.png">
</a>


<br clear="left"/>


## Project structure 

```
├── monitor_infra
│   ├── docker-compose.yml
│   ├── opentelemetry-javaagent.jar
│   └── otel-collector-config.yaml
├── weather-aggregator
│   ├── ci.sh
│   ├── Dockerfile
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
└── weather-gateway
    ├── ci.sh
    ├── Dockerfile
    ├── mvnw
    ├── mvnw.cmd
    ├── opentelemetry-javaagent.jar
    ├── pom.xml
    ├── src

```
