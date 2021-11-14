#!/bin/bash

./mvnw spring-boot:run & echo $! > ./pid.file &
