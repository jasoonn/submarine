#!/usr/bin/env bash
#/**
# * Licensed to the Apache Software Foundation (ASF) under one
# * or more contributor license agreements.  See the NOTICE file
# * distributed with this work for additional information
# * regarding copyright ownership.  The ASF licenses this file
# * to you under the Apache License, Version 2.0 (the
# * "License"); you may not use this file except in compliance
# * with the License.  You may obtain a copy of the License at
# *
# *     http://www.apache.org/licenses/LICENSE-2.0
# *
# * Unless required by applicable law or agreed to in writing, software
# * distributed under the License is distributed on an "AS IS" BASIS,
# * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# * See the License for the specific language governing permissions and
# * limitations under the License.
# */

rm -rf gen-java
mkdir gen-java
rm -rf ../java/org/apache/submarine/tony/rpc/proto
protoc --java_out=gen-java tensorflow_cluster_service_protos.proto
protoc --java_out=gen-java yarn_tensorflow_cluster_protos.proto
for file in gen-java/org/apache/submarine/tony/rpc/proto/* ; do
  cat java_license_header.txt ${file} > ${file}.tmp
  mv -f ${file}.tmp ${file}
done
mv gen-java/org/apache/submarine/tony/rpc/proto ../java/org/apache/submarine/tony/rpc/proto
rm -rf gen-java