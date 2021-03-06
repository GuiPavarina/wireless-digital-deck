# Wireless Digital Deck

## Requisitos

* docker
* docker-compose
* jq
* python3

## Infra

# Mapa de Portas do Docker

| Serviço | Porta (Externa) |
| ----------- | ----------- |
| POSTGRES KONG | 15432 |
| KONG PUBLIC HTTP | 8000 |
| KONG PUBLIC HTTPS | 8443 |
| KONG ADMIN HTTP | 8001 |
| KONG ADMIN HTTPS | 8444 |
| POSTGRES KEYCLOAK | 25432 |
| KEYCLOAK | 8180 |

### Scripts

start.sh -> inicia a aplicação do zero
stopAll.sh -> para a aplicação e remove os containers e os volumes

### LINKs

[Keycloak Admin Console](http://localhost:8180)

### Exportar variaveis de ambiente

```bash
# para dev
export HOST_IP=`ip address show dev docker0 | grep "inet " \
| grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' \
| head -1`

# do keycloak
export CLIENT_SECRET=""

```