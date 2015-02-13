/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.ignitefs;

import java.util.*;

import static org.apache.ignite.internal.util.ipc.shmem.IpcSharedMemoryServerEndpoint.*;

/**
 * GGFS Hadoop file system IPC loopback self test.
 */
public abstract class IgfsHadoopFileSystemLoopbackAbstractSelfTest extends
    IgfsHadoopFileSystemAbstractSelfTest {
    /**
     * Constructor.
     *
     * @param mode GGFS mode.
     * @param skipEmbed Skip embedded mode flag.
     */
    protected IgfsHadoopFileSystemLoopbackAbstractSelfTest(IgfsMode mode, boolean skipEmbed) {
        super(mode, skipEmbed, true);
    }

    /** {@inheritDoc} */
    @Override protected Map<String, String> primaryIpcEndpointConfiguration(final String gridName) {
        return new HashMap<String, String>() {{
            put("type", "tcp");
            put("port", String.valueOf(DFLT_IPC_PORT + getTestGridIndex(gridName)));
        }};
    }
}
