#!/usr/bin/env bash

GATLING_CONF=./conf ../../gatling-charts-highcharts-2.0.0-RC5/bin/gatling.sh -sf ./src -bf ./request-bodies -s com.puppetlabs.gatling.simulations.catalog_zero.FOSS370PuppetConf2
