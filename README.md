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

## Prerequisites
- Docker
- Docker Compose
- Java 11+
- Maven (Optional)

> **Note:** This project uses opentelemetry collectors and agent, so we can integrate them with any stack such as Elasticsearch/Kibana or Prometheus/Grafana 

## Setup steps
1. Create docker image for weather-gateway and weather-aggregator:
   - For Linux: Create docker image for services. Go to folder and run ci.sh shell script.
   This script will create a Spring boot JAR and create a docker image with that JAR.
    ```
    cd weather-aggregator
    sh ci.sh
    ```
    ```
    cd weather-gateway
    sh ci.sh
    ```
   - For Windows: 
    ```
    cd weather-aggregator
    mvn clean install
    docker build -t weather-aggregator .
    ```
    ```
    cd weather-gateway
    mvn clean install
    docker build -t weather-gateway .
    ```
