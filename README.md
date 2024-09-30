# spring-boot-observability-stack

This project demonstrates how to integrate OpenTelemetry with a Spring Boot application to collect and send logs, traces, and metrics to Elasticsearch, Kibana, and Elastic APM. 
The project consists of the following components:


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
1. Create Docker images for `weather-gateway` and `weather-aggregator`:
   - For Linux: To create Docker images for the services, navigate to the appropriate folder and run the `ci.sh` shell script.
     This script will build the Spring Boot JAR file and create a Docker image containing that JAR.
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
2. Run the Docker Compose file
   - The Docker Compose file contains the following components: Kibana, Elasticsearch, APM Server, Otel Collector, Weather Aggregator, and Weather Gateway. 
     All components run on the same network, allowing them to communicate with each other.
     To create and start these components, run the following command:
     ```
     cd monitor_infra
     docker compose up -d
     ```
3. To check Kibana, open your browser and navigate to `http://localhost:5601`.
4. If Kibana is up and running, you can hit the API using the following command in the terminal:

```bash
for i in {1..100}; do curl --location 'localhost:8082/v1/api/weather?lat=52.52&lon=13.41'; done
```
After that, you will be able to see the data in the APM section of Kibana. 
For logs, you need to configure data views in Kibana to visualize them.

