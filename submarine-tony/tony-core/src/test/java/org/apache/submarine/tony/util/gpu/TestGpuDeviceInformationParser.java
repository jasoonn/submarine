/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.submarine.tony.util.gpu;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Ported from Hadoop 2.9.0
 */
public class TestGpuDeviceInformationParser {
  @Test
  public void testParse() throws IOException, GpuInfoException {
    File f = new File("tony-core/src/test/resources/nvidia-smi-sample-xml-output");
    String s = FileUtils.readFileToString(f, "UTF-8");

    GpuDeviceInformationParser parser = new GpuDeviceInformationParser();

    GpuDeviceInformation info = parser.parseXml(s);
    Assert.assertEquals("375.66", info.getDriverVersion());
    Assert.assertEquals(2, info.getGpus().size());
    PerGpuDeviceInformation gpu1 = info.getGpus().get(1);
    Assert.assertEquals("Tesla P100-PCIE-12GB", gpu1.getProductName());
    Assert.assertEquals(12193, gpu1.getGpuFBMemoryUsage().getTotalMemoryMiB());
    Assert.assertEquals(16384, gpu1.getGpuMainMemoryUsage().getTotalMemoryMiB());
    Assert.assertEquals(10.3f,
        gpu1.getGpuUtilizations().getOverallGpuUtilization(), 1e-6);
  }
}