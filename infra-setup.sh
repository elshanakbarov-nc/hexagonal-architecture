#!/usr/bin/env bash

# NETWORK
TEST_NETWORK='route_to_go_network'

# DB
TEST_DB_DATA='route_to_go_test_db_data'
TEST_DB_NAME='route_to_go_test_db'
TEST_DB_IMAGE='mariadb:10.5.8'
TEST_DB_PASSWORD='password'
TEST_DB_PORT=4306

if [ -n "$DB_PORT" ]; then
    echo "Database port is set as $DB_PORT"
    TEST_DB_PORT=$DB_PORT
fi


#####################################
# DOCKER
#####################################

isUp() {
    # shellcheck disable=SC2086
    if [[ -n $(lsof -Pi :$1) ]]; then
        echo 1
    fi
}

isDown() {
    # shellcheck disable=SC2086
    if [[ -z $(lsof -Pi :$1) ]]; then
        echo 1
    fi
}

create_volume(){
    if ! (docker volume ls | grep "${1}");
    then
       echo ">> Creating ${1} volume"
       docker volume create "${1}"
    fi
}


create_network(){
    if ! (docker network ls | grep "${1}");
    then
       echo ">> Creating ${1} network"
       docker network create "${1}"
    fi
}

#####################################
# DATABASE
#####################################


isDBUp() {
    x=1
    while [[ $(docker logs $TEST_DB_NAME 2>/dev/null --tail 100 ) != *"started"* ]] && [  ${x} -le 30 ]; do
        sleep 1;
        x=$(( x + 1 ))
        printf "."
    done

    if [[ $(docker logs $TEST_DB_NAME 2>/dev/null --tail 100 ) != *"started"* ]]; then
        printf "DB cannot be boot up in %s seconds, quited." "$x"
        exit 1
    else
        printf "DB becomes up and running in %s seconds\n" "$x"
    fi
}

up_db() {
    if [[ ! $(isUp 4306) ]]; then
        echo ">> starting: db up"
        create_volume $TEST_DB_DATA
        create_network $TEST_NETWORK
        docker run --rm --network ${TEST_NETWORK} --name ${TEST_DB_NAME} --mount source=${TEST_DB_DATA},target=/var/lib/mysql --env MYSQL_ROOT_PASSWORD=${TEST_DB_PASSWORD} -p ${TEST_DB_PORT}:3306 -d ${TEST_DB_IMAGE}
        isDBUp
    else
        echo ">> db is already up"
    fi
}

down_db() {
    if [[ ! $(isDown "${TEST_DB_PORT}") ]]; then
        echo ">> down db"
        docker rm -v -f ${TEST_DB_NAME}
        docker volume rm ${TEST_DB_DATA}
    else
        echo ">> db is already down"
    fi
}

case "${1}" in
    "db")
        case "${2}" in
        "up") echo Starting db && up_db;;
        "down") echo Stopping db && down_db;;
        esac;;
    "infra")
        case "${2}" in
        "up")
            echo Starting db && up_db;
            ;;
        "down")
            echo Stopping db && down_db;
            ;;
        esac
        ;;
    *)
        echo Unknown command! "${1}"
esac

