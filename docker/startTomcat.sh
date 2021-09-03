#!/bin/sh
if [ -r /opt/app/setenv.sh ]; then
  . /opt/app/setenv.sh
fi
exec java $CATALINA_OPTS -jar $ENV_APP_FILE_PATH