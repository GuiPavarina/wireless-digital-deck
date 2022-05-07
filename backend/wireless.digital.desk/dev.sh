mvn clean install

docker build -t wireless-digital-desk-server:latest .

cd ../../infra

docker-compose stop wireless-digital-desk-server

docker-compose up -d wireless-digital-desk-server
