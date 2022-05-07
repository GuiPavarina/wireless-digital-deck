admin_route_id=$(curl http://localhost:8001/routes | jq '.data[] | [.paths[0], .id] | join("|")' | grep "/admin" | sed 's/\/admin|//g' | sed 's/\"//g')

echo $admin_route_id

api_route_id=$(curl http://localhost:8001/routes | jq '.data[] | [.paths[0], .id] | join("|")' | grep "/api/v1" | sed 's/\/api\/v1|//g' | sed 's/\"//g')

echo $api_route_id

source ../config.sh

curl -X POST http://localhost:8001/routes/${admin_route_id}/plugins \
  -d name=oidc \
  -d config.client_id=${CLIENT_ID} \
  -d config.client_secret=${CLIENT_SECRET} \
  -d config.realm=${REALM} \
  -d config.introspection_endpoint=http://${HOST_IP}:8180/auth/realms/${REALM}/protocol/openid-connect/token/introspect \
  -d config.discovery=http://${HOST_IP}:8180/auth/realms/${REALM}/.well-known/openid-configuration \
  | python3 -mjson.tool

curl -X POST http://localhost:8001/routes/${api_route_id}/plugins \
  -d name=oidc \
  -d config.client_id=${CLIENT_ID} \
  -d config.client_secret=${CLIENT_SECRET} \
  -d config.realm=${REALM} \
  -d config.introspection_endpoint=http://${HOST_IP}:8180/auth/realms/${REALM}/protocol/openid-connect/token/introspect \
  -d config.discovery=http://${HOST_IP}:8180/auth/realms/${REALM}/.well-known/openid-configuration \
  | python3 -mjson.tool

