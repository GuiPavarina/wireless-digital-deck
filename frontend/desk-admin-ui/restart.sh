ng build --prod

docker build -t desk-admin-ui:latest .

cd ../../infra

docker-compose up -d desk-admin-ui