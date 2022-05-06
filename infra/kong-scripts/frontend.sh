curl -s -X POST http://localhost:8001/services \
    -d name=desk-ui-service \
    -d url=http://172.17.0.1:9000/ \
    | python3 -mjson.tool

curl -s -X POST http://localhost:8001/services/desk-ui-service/routes -d "paths[]=/admin" \
    | python3 -mjson.tool
