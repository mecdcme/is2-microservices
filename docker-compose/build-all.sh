cd ../is2-logger
mvn clean install -DskipTests
docker build -t mecdcme/is2-logger .
cd ../is2-dataset
mvn clean install -DskipTests
docker build -t mecdcme/is2-dataset .
cd ../is2-workflow-monitor
mvn clean install -DskipTests
docker build -t mecdcme/is2-workflow-monitor .
cd ../is2-notificator
mvn clean install -DskipTests
docker build -t mecdcme/is2-notificator .
cd ../is2-api-gateway
mvn clean install -DskipTests
docker build -t mecdcme/is2-api-gateway .