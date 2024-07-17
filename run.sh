#!/bin/bash

./mvnw clean package

java -jar target/hotel-0.0.1-SNAPSHOT.jar
