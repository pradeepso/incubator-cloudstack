// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.storage.pool;

import java.util.Date;

import com.cloud.storage.pool.Storage.StoragePoolType;

public interface StoragePool {

    /**
     * @return id of the pool.
     */
    long getId();

    /**
     * @return name of the pool.
     */
    String getName();

    /***
     * 
     * @return unique identifier
     */
    String getUuid();

    /**
     * @return the type of pool.
     */
    StoragePoolType getPoolType();

    /**
     * @return the date the pool first registered
     */
    Date getCreated();

    /**
     * @return the last time the state of this pool was modified.
     */
    Date getUpdateTime();

    /**
     * @return availability zone.
     */
    long getDataCenterId();

    /**
     * @return capacity of storage poolin bytes
     */
    long getCapacityBytes();

    /**
     * @return available storage in bytes
     */
    long getAvailableBytes();

    Long getClusterId();

    /**
     * @return the fqdn or ip address of the storage host
     */
    String getHostAddress();

    /**
     * @return the filesystem path of the pool on the storage host (server)
     */
    String getPath();

    /**
     * @return the user information / credentials for the storage host
     */
    String getUserInfo();

    /**
     * @return the storage pool represents a shared storage resource
     */
    boolean isShared();

    /**
     * @return the storage pool represents a local storage resource
     */
    boolean isLocal();

    /**
     * @return the storage pool status
     */
    StoragePoolStatus getStatus();

    int getPort();

    Long getPodId();
}