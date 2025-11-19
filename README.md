This is a Microservice architechture based on apigateway using eureka client service registry also resilence4j for circuit bracker and retry for service working check
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Run our custom batch ddl
spring.sql.init.schema-locations=classpath:batch-schema.sql
spring.sql.init.mode=always

# Do NOT let Spring Batch auto-init anymore
spring.batch.jdbc.initialize-schema=never

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console    
