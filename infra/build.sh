cd ../frontend/desk-admin-ui/

ng build --prod

docker build -t desk-admin-ui:latest .

cd ../../backend/wireless.digital.desk/

mvn clean install

docker build -t wireless-digital-desk-server:latest .

