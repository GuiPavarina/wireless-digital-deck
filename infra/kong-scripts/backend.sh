curl -s -X POST http://localhost:8001/services \
    -d name=wireless-desk-service \
    -d url=http://172.17.0.1:9001/ \
    | python3 -mjson.tool

curl -s -X POST http://localhost:8001/services/wireless-desk-service/routes -d "paths[]=/api/v1" \
    | python3 -mjson.tool

curl -s -X POST http://localhost:8001/services/wireless-desk-service/routes -d "paths[]=/api/public" \
    | python3 -mjson.tool

