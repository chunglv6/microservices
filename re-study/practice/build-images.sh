#! /bin/bash
d=$(pwd)
echo $d

cd "$d/accounts"

mvn spring-boot:build-image -Dmaven.test.skip=true
cd "$d/loans"
mvn spring-boot:build-image -Dmaven.test.skip=true
cd "$d/cards"
mvn spring-boot:build-image -Dmaven.test.skip=true

cd "$d/configserver"
mvn spring-boot:build-image -Dmaven.test.skip=true
cd "$d/eurekaserver"
mvn spring-boot:build-image -Dmaven.test.skip=true

cd "$d/gatewayserver"
mvn spring-boot:build-image -Dmaven.test.skip=true
