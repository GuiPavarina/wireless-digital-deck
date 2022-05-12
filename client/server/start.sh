cd ../ui/client-ui

npm i

ng build

cd ../../server/

cp -r ../ui/client-ui/dist/client-ui dist/

node server.js
