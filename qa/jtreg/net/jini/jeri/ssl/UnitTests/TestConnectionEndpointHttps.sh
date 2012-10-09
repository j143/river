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
# test -- Disable for now. -tjb[28.Apr.2003]
# @summary Tests the SecureConnectionEndpoint methods provided by the Endpoint
#	   class over HTTPS.
# 
# @library ../../../unittestlib
# @build UnitTestUtilities BasicTest Test
# @build TestEndpoint TestEndpointUtilities TestUtilities
# @run main/othervm/policy=policy/timeout=250 -DuseHttps=true
#      TestConnectionEndpoint
