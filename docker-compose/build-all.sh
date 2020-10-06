cd ../logger
mvn clean install -DskipTests
docker build -t mecdcme/is2-logger .
cd ../dataset
mvn clean install -DskipTests
docker build -t mecdcme/is2-dataset .
cd ../workflow-monitor
mvn clean install -DskipTests
docker build -t mecdcme/is2-workflow-monitor .
cd ../notificator
mvn clean install -DskipTests
docker build -t mecdcme/is2-notificator .
