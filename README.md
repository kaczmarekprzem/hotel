# Hotel Room Allocation

## Overview

This project is a room occupancy optimization tool for hotel clients. It helps in allocating rooms to guests based on their willingness to pay and the availability of premium and economy rooms. The project includes a REST API that allows hotels to input the number of available rooms and the list of potential guests, and it returns the occupancy and revenue details.

## Features
- Optimize room occupancy based on guest willingness to pay.
- Allocate guests to Premium and Economy rooms based on availability and preferences.
- RESTful API for managing room allocation.
- Swagger integration for API documentation.
- Docker support for containerization.

## Requirements

- Java 17
- Maven
- Docker

## Building and Running the Application

### Using Docker

1. **Build the Docker Image**:

   Navigate to the root directory of the project and run the following command to build the Docker image:

   ```sh
   docker build -t hotel-occupancy .


2. **Run the Docker Container**:

   After building the image, run the following command to start the container:

   ```sh
   docker run -p 8080:8080 hotel-occupancy

## API Documentation

The API documentation is available via Swagger UI. Once the application is running, you can access the documentation at:
```agsl
http://localhost:8080/swagger-ui.html
```

## REST API

Calculate Room Occupancy and Revenue:

- Endpoint: /occupancy
- Method: POST
- Content-Type: application/json

## Request Body:

```agsl
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

## Response Body:

```agsl
{
"usagePremium": 6,
"revenuePremium": 1054,
"usageEconomy": 4,
"revenueEconomy": 189.99
}
```
