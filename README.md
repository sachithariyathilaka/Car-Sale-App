# Car Sale App
Car Sale Back end REST API

# Technologies

Java, Spring boot, MYSQL

# Docker Commands

Step 01: docker pull mysql:5.7

Step 02: docker images

Step 03: docker network create springboot-mysql-network

Step 04: docker network ls

Step 05: docker run --name mysqldb --network springboot-mysql-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=carsale -d mysql:5.7

Step 06: docker exec -it c23 bash

Step 07: mysql -uroot -proot

Step 08: show databases;

Step 09: mvn clean install -DskipTests

Step 10: docker build -t carsale-backend .

Step 11: docker run --network springboot-mysql-network --name springboot-container -p 8081:8081 -d carsale-backend

Step 12: docker ps

Step 13: docker logs 1c4

Step 14: docker stop mysqldb

Step 15: docker stop carsale-app-container

Step 16: docker start mysqldb

Step 17: docker start carsale-app-container
