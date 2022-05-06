source ../config.sh

curl -X POST http://localhost:8001/routes/cec713fc-02e1-4c5e-83d7-f83b8aefc6d6/plugins \
  -d name=oidc \
  -d config.client_id=${CLIENT_ID} \
  -d config.client_secret=${CLIENT_SECRET} \
  -d config.realm=${REALM} \
  -d config.introspection_endpoint=http://${HOST_IP}:8180/auth/realms/${REALM}/protocol/openid-connect/token/introspect \
  -d config.discovery=http://${HOST_IP}:8180/auth/realms/${REALM}/.well-known/openid-configuration \
  | python3 -mjson.tool

curl -X POST http://localhost:8001/routes/c17d1fbc-15ef-4834-ad99-65a291a20fd5/plugins \
  -d name=oidc \
  -d config.client_id=${CLIENT_ID} \
  -d config.client_secret=${CLIENT_SECRET} \
  -d config.realm=${REALM} \
  -d config.introspection_endpoint=http://${HOST_IP}:8180/auth/realms/${REALM}/protocol/openid-connect/token/introspect \
  -d config.discovery=http://${HOST_IP}:8180/auth/realms/${REALM}/.well-known/openid-configuration \
  | python3 -mjson.tool

