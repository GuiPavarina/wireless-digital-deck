cd ../../client/server/

./build.sh

cd ../../frontend/desk-admin-ui/

cp ../../client/server/client.tar.gz src/assets/

npm install

ng build --prod

docker build -t desk-admin-ui:latest .

cd ../../infra

docker-compose up -d desk-admin-ui