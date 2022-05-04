docker-compose build kong
docker-compose up -d kong-db

# wait until postgres is ok to run migrations
sleep 30s

# run kong migrations
docker-compose run --rm kong kong migrations bootstrap
docker-compose run --rm kong kong migrations up
docker-compose up -d kong
docker-compose ps

sleep 30s

# starting keycloak
docker-compose up -d keycloak-db
docker-compose up -d keycloak
docker-compose ps
