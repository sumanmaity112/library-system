#!/usr/bin/env bash
set -eu

SCRIPT_DIR=$(cd "$(dirname "$0")" ; pwd -P)

goal_docker-build(){
    pushd "${SCRIPT_DIR}" >/dev/null
    docker build -t library-system-postgres .
    popd >/dev/null
}

goal_docker-run(){
    PASSWORD=${1:-password}
    pushd "${SCRIPT_DIR}" >/dev/null
    docker run -p 5432:5432 -e POSTGRES_PASSWORD=${PASSWORD} --name library-system-postgres -d library-system-postgres
    popd >/dev/null
}

goal_docker-restart(){
    pushd "${SCRIPT_DIR}" >/dev/null
    docker stop library-system-postgres > /dev/null
    docker start library-system-postgres > /dev/null
    popd >/dev/null
}

goal_psql(){
    DB_NAME=${1:-library}
    USERNAME=${2:-library_admin}
    PASSWORD=${3:-password}

    pushd "${SCRIPT_DIR}" >/dev/null
    PGPASSWORD=${PASSWORD} psql -h 127.0.0.1 -p 5432 -U ${USERNAME} ${DB_NAME}
    popd >/dev/null
}

if type -t "goal_$1" &>/dev/null; then
  goal_$1 ${@:2}
else
  echo "usage: $0 <goal>

goal:
    docker-build        Build docker image
    docker-run          Create library-system-postgres docker container and expose 5432 port to access it
    docker-restart      Restart library-system-postgres docker container
    psql                Connect to psql interactive shell of library-system-postgres docker container
"
  exit 1
fi
