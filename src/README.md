**Customer service application** 
An application which maintains the history of the order snapshot of Cleanhub customers. 


 - java 11
 - spring boot 2.7
 - Database H2

Steps to build
 - mvn clean compile
 - mvn spring-boot:run

API exposed :
Example : `curl -X GET http://localhost:8081/customers/top-customers-by-order-growth\?limit=2`
