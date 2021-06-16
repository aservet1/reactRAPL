#!/bin/bash
cmd='sudo java -cp target/httpRaplServer-1.0.jar httpRaplServer.HttpRAPL | tee last-server-run.log'
echo $cmd; eval $cmd
