# Device-allocation
This is a spring-boot application for demo purpose only.

It uses H2 in memory db to persist data and RabbitMQ to push notifications.

It has 3 services: 
1. Health : This is to check if service is up and running, everything is fine, it will return ok response.
2. Book device: 
Endpoint : http://localhost:9090/book_device
Method : PUT
example request body: 
{
    "deviceId":"1",
    "userId":"5"
}
response: Success or Fail

3. Return device:
Endpoint: http://localhost:9090/return_device
example request body:
{
    "deviceId":"1",
    "userId":"5"
}
response: Success or Fail

To check device and user data, please use H2 console, which is accessable at http://localhost:9090/h2/
please input jdbc:h2:file:./device-db as JDBC URL.
User name = sa and password blank




