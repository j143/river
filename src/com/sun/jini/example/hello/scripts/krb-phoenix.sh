#! /bin/sh
#/*
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership. The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License. You may obtain a copy of the License at
# 
#      http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#*/

# Shell script to run Kerberos Phoenix

# The server host
host=`hostname`

# Get the environment variables setup
. scripts/krb-setenv.sh

set -ex

java -Djava.security.manager= 						\
     -Djava.security.policy=config/krb-phoenix.policy 			\
     -Djava.security.auth.login.config=config/krb-phoenix.login 	\
     -Djava.protocol.handler.pkgs=net.jini.url		 		\
     -Djava.rmi.server.RMIClassLoaderSpi=com.sun.jini.example.hello.MdClassAnnotationProvider \
     -Dexport.codebase.source.jsk=../../../../../../../lib-dl		\
     -Dexport.codebase.jsk="httpmd://$host:8080/phoenix-dl.jar;sha=0 httpmd://$host:8080/jsk-dl.jar;sha=0" 	\
     -DclientPrincipal="$CLIENT"@"$REALM" 				\
     -DserverPrincipal="$SERVER"@"$REALM" 				\
     -DphoenixPrincipal="$PHOENIX"@"$REALM" 				\
     -DreggiePrincipal="$REGGIE"@"$REALM" 				\
     -Djava.security.krb5.realm=$REALM 					\
     -Djava.security.krb5.kdc=$KDC_HOST 				\
     -classpath ../../../../../../../lib/phoenix.jar:lib/mdprefld.jar 	\
     com.sun.jini.phoenix.Activation 					\
     config/krb-phoenix.config
