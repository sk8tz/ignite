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

package org.apache.ignite.internal.processors.fs;

import org.apache.ignite.*;
import org.apache.ignite.configuration.*;
import org.apache.ignite.ignitefs.*;
import org.apache.ignite.ignitefs.mapreduce.*;
import org.apache.ignite.internal.*;
import org.apache.ignite.internal.util.typedef.internal.*;
import org.apache.ignite.lang.*;
import org.jetbrains.annotations.*;

import java.net.*;
import java.util.*;

/**
 * Ggfs supporting asynchronous operations.
 */
public class IgfsAsyncImpl extends AsyncSupportAdapter<IgniteFs> implements IgfsEx {
    /** */
    private final IgfsImpl ggfs;

    /**
     * @param ggfs Ggfs.
     */
    public IgfsAsyncImpl(IgfsImpl ggfs) {
        super(true);

        this.ggfs = ggfs;
    }

    /** {@inheritDoc} */
    @Override public void format() {
        try {
            saveOrGet(ggfs.formatAsync());
        }
        catch (IgniteCheckedException e) {
            throw U.convertException(e);
        }
    }

    /** {@inheritDoc} */
    @Override public <T, R> R execute(IgfsTask<T, R> task, @Nullable IgfsRecordResolver rslvr,
        Collection<IgfsPath> paths, @Nullable T arg) {
        try {
            return saveOrGet(ggfs.executeAsync(task, rslvr, paths, arg));
        }
        catch (IgniteCheckedException e) {
            throw U.convertException(e);
        }
    }

    /** {@inheritDoc} */
    @Override public <T, R> R execute(IgfsTask<T, R> task, @Nullable IgfsRecordResolver rslvr,
        Collection<IgfsPath> paths, boolean skipNonExistentFiles, long maxRangeLen, @Nullable T arg) {
        try {
            return saveOrGet(ggfs.executeAsync(task, rslvr, paths, skipNonExistentFiles, maxRangeLen, arg));
        }
        catch (IgniteCheckedException e) {
            throw U.convertException(e);
        }
    }

    /** {@inheritDoc} */
    @Override public <T, R> R execute(Class<? extends IgfsTask<T, R>> taskCls,
        @Nullable IgfsRecordResolver rslvr, Collection<IgfsPath> paths, @Nullable T arg) {
        try {
            return saveOrGet(ggfs.executeAsync(taskCls, rslvr, paths, arg));
        }
        catch (IgniteCheckedException e) {
            throw U.convertException(e);
        }
    }

    /** {@inheritDoc} */
    @Override public <T, R> R execute(Class<? extends IgfsTask<T, R>> taskCls,
        @Nullable IgfsRecordResolver rslvr, Collection<IgfsPath> paths, boolean skipNonExistentFiles,
        long maxRangeLen, @Nullable T arg) {
        try {
            return saveOrGet(ggfs.executeAsync(taskCls, rslvr, paths, skipNonExistentFiles, maxRangeLen, arg));
        }
        catch (IgniteCheckedException e) {
            throw U.convertException(e);
        }
    }

    /** {@inheritDoc} */
    @Override public void stop() {
        ggfs.stop();
    }

    /** {@inheritDoc} */
    @Override public IgfsContext context() {
        return ggfs.context();
    }

    /** {@inheritDoc} */
    @Override public IgfsPaths proxyPaths() {
        return ggfs.proxyPaths();
    }

    /** {@inheritDoc} */
    @Override public IgfsInputStreamAdapter open(IgfsPath path, int bufSize,
        int seqReadsBeforePrefetch) {
        return ggfs.open(path, bufSize, seqReadsBeforePrefetch);
    }

    /** {@inheritDoc} */
    @Override public IgfsInputStreamAdapter open(IgfsPath path) {
        return ggfs.open(path);
    }

    /** {@inheritDoc} */
    @Override public IgfsInputStreamAdapter open(IgfsPath path, int bufSize) {
        return ggfs.open(path, bufSize);
    }

    /** {@inheritDoc} */
    @Override public IgfsStatus globalSpace() throws IgniteCheckedException {
        return ggfs.globalSpace();
    }

    /** {@inheritDoc} */
    @Override public void globalSampling(@Nullable Boolean val) throws IgniteCheckedException {
        ggfs.globalSampling(val);
    }

    /** {@inheritDoc} */
    @Nullable @Override public Boolean globalSampling() {
        return ggfs.globalSampling();
    }

    /** {@inheritDoc} */
    @Override public IgfsLocalMetrics localMetrics() {
        return ggfs.localMetrics();
    }

    /** {@inheritDoc} */
    @Override public long groupBlockSize() {
        return ggfs.groupBlockSize();
    }

    /** {@inheritDoc} */
    @Override public IgniteInternalFuture<?> awaitDeletesAsync() throws IgniteCheckedException {
        return ggfs.awaitDeletesAsync();
    }

    /** {@inheritDoc} */
    @Nullable @Override public String clientLogDirectory() {
        return ggfs.clientLogDirectory();
    }

    /** {@inheritDoc} */
    @Override public void clientLogDirectory(String logDir) {
        ggfs.clientLogDirectory(logDir);
    }

    /** {@inheritDoc} */
    @Override public boolean evictExclude(IgfsPath path, boolean primary) {
        return ggfs.evictExclude(path, primary);
    }

    /** {@inheritDoc} */
    @Override public IgniteUuid nextAffinityKey() {
        return ggfs.nextAffinityKey();
    }

    /** {@inheritDoc} */
    @Override public boolean isProxy(URI path) {
        return ggfs.isProxy(path);
    }

    /** {@inheritDoc} */
    @Nullable @Override public String name() {
        return ggfs.name();
    }

    /** {@inheritDoc} */
    @Override public IgniteFsConfiguration configuration() {
        return ggfs.configuration();
    }

    /** {@inheritDoc} */
    @Override public IgfsPathSummary summary(IgfsPath path) {
        return ggfs.summary(path);
    }

    /** {@inheritDoc} */
    @Override public IgfsOutputStream create(IgfsPath path, boolean overwrite) {
        return ggfs.create(path, overwrite);
    }

    /** {@inheritDoc} */
    @Override public IgfsOutputStream create(IgfsPath path, int bufSize, boolean overwrite, int replication,
        long blockSize, @Nullable Map<String, String> props) {
        return ggfs.create(path, bufSize, overwrite, replication, blockSize, props);
    }

    /** {@inheritDoc} */
    @Override public IgfsOutputStream create(IgfsPath path, int bufSize, boolean overwrite,
        @Nullable IgniteUuid affKey, int replication, long blockSize, @Nullable Map<String, String> props) {
        return ggfs.create(path, bufSize, overwrite, affKey, replication, blockSize, props);
    }

    /** {@inheritDoc} */
    @Override public IgfsOutputStream append(IgfsPath path, boolean create) {
        return ggfs.append(path, create);
    }

    /** {@inheritDoc} */
    @Override public IgfsOutputStream append(IgfsPath path, int bufSize, boolean create,
        @Nullable Map<String, String> props) {
        return ggfs.append(path, bufSize, create, props);
    }

    /** {@inheritDoc} */
    @Override public void setTimes(IgfsPath path, long accessTime, long modificationTime) {
        ggfs.setTimes(path, accessTime, modificationTime);
    }

    /** {@inheritDoc} */
    @Override public Collection<IgfsBlockLocation> affinity(IgfsPath path, long start, long len) {
        return ggfs.affinity(path, start, len);
    }

    /** {@inheritDoc} */
    @Override public Collection<IgfsBlockLocation> affinity(IgfsPath path, long start, long len, long maxLen) {
        return ggfs.affinity(path, start, len, maxLen);
    }

    /** {@inheritDoc} */
    @Override public IgfsMetrics metrics() {
        return ggfs.metrics();
    }

    /** {@inheritDoc} */
    @Override public void resetMetrics() {
        ggfs.resetMetrics();
    }

    /** {@inheritDoc} */
    @Override public long size(IgfsPath path) {
        return ggfs.size(path);
    }

    /** {@inheritDoc} */
    @Override public boolean exists(IgfsPath path) {
        return ggfs.exists(path);
    }

    /** {@inheritDoc} */
    @Nullable @Override public IgfsFile update(IgfsPath path, Map<String, String> props) {
        return ggfs.update(path, props);
    }

    /** {@inheritDoc} */
    @Override public void rename(IgfsPath src, IgfsPath dest) {
        ggfs.rename(src, dest);
    }

    /** {@inheritDoc} */
    @Override public boolean delete(IgfsPath path, boolean recursive) {
        return ggfs.delete(path, recursive);
    }

    /** {@inheritDoc} */
    @Override public void mkdirs(IgfsPath path) {
        ggfs.mkdirs(path);
    }

    /** {@inheritDoc} */
    @Override public void mkdirs(IgfsPath path, @Nullable Map<String, String> props) {
        ggfs.mkdirs(path, props);
    }

    /** {@inheritDoc} */
    @Override public Collection<IgfsPath> listPaths(IgfsPath path) {
        return ggfs.listPaths(path);
    }

    /** {@inheritDoc} */
    @Override public Collection<IgfsFile> listFiles(IgfsPath path) {
        return ggfs.listFiles(path);
    }

    /** {@inheritDoc} */
    @Nullable @Override public IgfsFile info(IgfsPath path) {
        return ggfs.info(path);
    }

    /** {@inheritDoc} */
    @Override public long usedSpaceSize() {
        return ggfs.usedSpaceSize();
    }

    /** {@inheritDoc} */
    @Override public Map<String, String> properties() {
        return ggfs.properties();
    }
}
