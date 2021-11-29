#!/usr/bin/env bash
case "${1}" in
    "infra")
        case "${2}" in
        "up")
            echo Starting infra && docker-compose up -d;
            ;;
        "down")
            echo Stopping infra && docker-compose rm -fsv;
            ;;
        esac
        ;;
    *)
        echo Unknown command! "${1}"
esac

