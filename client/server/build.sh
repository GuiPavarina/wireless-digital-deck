cd ../ui/client-ui

ng build

cd ../../server/

cp -r ../ui/client-ui/dist/client-ui dist/

tar -czvf client.tar.gz api db dist .env package.json server.js 


