#!/bin/bash


find /app -name "*.java" > sources.txt
javac -cp $CATALINA_HOME/lib/*:/app/WEB-INF/lib/* @sources.txt
rm sources.txt

cd /app
rm -rf $CATALINA_HOME/webapps/*
jar -cvf $CATALINA_HOME/webapps/ROOT.war *
cd -

catalina.sh run
