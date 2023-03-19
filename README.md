# kafka-reporter 
Report Application powered by Spring Boot that subscribes to 
Kafka and consumes Order Creation response from this [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application) and persists the data.


The app also exposes a REST API endpoint that returns an Order report that is grouped by date.

The endpoint ULR => http://localhost:8081/api/v1/report/get-by-range takes POST request.

It takes a date range filter as a json RequestBody like this:

    {
        "startDate": "2023-03-16",
         "endDate": "2023-03-19"
    }

Then the API returns following fields:

* Date
* Total_Order -> the total number of orders for that date
* Total_Order_Amount -> the total sum of all the orders for that date

*In sum, it returns an array of order summary grouped by date*

Sample response:
```
[
    {
    "date": "2023-03-17",
    "totalOrderCount": 3,
    "totalOrderAmount": 1200.0
    
    },
    {
    "date": "2023-03-19",
    "totalOrderCount": 2,
    "totalOrderAmount": 900.0
    }
]
```
Or an empty array if orders have not been placed on the [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application)

____

More details on how to run the project is on the [Inventory / Sales Management App](https://github.com/Amy-Oji/sales-inventory-manager-REST-API-application) page.
