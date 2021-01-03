#!/bin/bash
cmd='sudo mvn clean install && sudo java -cp target/httpRaplServer-1.0.jar httpRaplServer.JavaHTTPServer'
echo $cmd
eval $cmd
