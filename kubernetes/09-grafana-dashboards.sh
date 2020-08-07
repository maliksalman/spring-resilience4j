#!/bin/bash

kubectl create configmap grafana-dashboards \
  --from-file=../docker/dashboards/backend-ops.json \
  --from-file=../docker/dashboards/circuit-breakers.json \
  --from-file=../docker/dashboards/jvm.json

kubectl label configmap grafana-dashboards app=grafana tier=monitoring
