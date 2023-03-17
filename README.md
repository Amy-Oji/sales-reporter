# kafka-reporter 
Report Application powered by Spring Boot that subscribes to 
Kafka and consumes Order Creation response from this [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application) and persists the data.


The app also exposes a REST API endpoint that returns an Order report that is grouped by date.

The endpoint ULR => http://localhost:8081/api/v1/report/get-by-range takes POST request.

It take a date range filter as a json RequestBody like this:

{

    "startDate": "2023-03-16",
     "endDate": "2023-03-17"
     
}

Then the API returns following fields:

* Date

* Total_Order -> the total number or orders within the date range

* Total_Order_Amount -> the total sum of all the orders within the date range

The respones =>

{

        "date": "2023-03-17",
        "totalOrderCount": 3,
        "totalOrderAmount": 1200.0
        
}

____
Details on how to run project is on the [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application) page.
