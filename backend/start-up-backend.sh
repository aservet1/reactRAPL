#!/bin/bash
cmd='sudo mvn clean install && sudo java -cp target/httpRaplServer-1.0.jar httpRaplServer.HttpRAPL'
echo $cmd
eval $cmd
