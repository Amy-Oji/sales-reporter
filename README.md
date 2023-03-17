# kafka-reporder
Report Application powered by Spring Boot that subscribes to 
Kafka and consumes Order Creation Payload from this [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application).


The app also exposes a REST API endpoint that returns an Order report that is grouped by date. It take a date range filter as a json RequestBody like this:
{
    "startDate": "2023-03-16",
     "endDate": "2023-03-17"
}  

Then the API returns following fields:

* Date

* Total_Order

* Total_Order_Amount

eg:
{
        "date": "2023-03-17",
        "totalOrderCount": 3,
        "totalOrderAmount": 1200.0
}
