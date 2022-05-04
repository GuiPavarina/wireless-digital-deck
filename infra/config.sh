HOST_IP=`ip address show dev docker0 | grep "inet " \
| grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' \
| head -1`

CLIENT_SECRET=""

REALM="deck"

CLIENT_ID="kong"
