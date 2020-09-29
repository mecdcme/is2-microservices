cd ../logger
mvn clean install -DskipTests
docker build -t istat/logger .
cd ../dataset
mvn clean install -DskipTests
docker build -t istat/dataset .
cd ../workflow-monitor
mvn clean install -DskipTests
docker build -t istat/workflow-monitor .
