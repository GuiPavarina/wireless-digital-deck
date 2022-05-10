docker build -t wireless-digital-desk-shared-server:latest .

cd ../../infra

docker-compose stop wireless-digital-desk-shared-server

docker-compose up -d wireless-digital-desk-shared-server
