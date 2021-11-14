#!/bin/bash

docker build -t elenagherrero/martian_robots:0.0.1 .
docker run -p 8080:8080 --name martinrobots elenagherrero/martian_robots:0.0.1