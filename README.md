# kafka-reporder
Report Application powered by Spring Boot that subscribes to 
Kafka and consumes Order Creation Payload from this [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application).


The app also exposes REST API endpoint that returns an Order report that is grouped by date. A date range filter should be supported. 
The following fields are returned by the API.

* Date

* Total_Order

* Total_Order_Amount
