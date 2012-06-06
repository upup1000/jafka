/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sohu.jafka.network.handlers;

import com.sohu.jafka.api.DeleterRequest;
import com.sohu.jafka.api.RequestKeys;
import com.sohu.jafka.log.LogManager;
import com.sohu.jafka.network.NumbersSend.IntegersSend;
import com.sohu.jafka.network.Receive;
import com.sohu.jafka.network.Send;

/**
 * @author adyliu (imxylz@gmail.com)
 * @since 1.2
 */
public class DeleterHandler extends AbstractHandler {

    public DeleterHandler(LogManager logManager) {
        super(logManager);
    }

    @Override
    public Send handler(RequestKeys requestType, Receive request) {
        DeleterRequest deleterRequest = DeleterRequest.readFrom(request.buffer());
        if (logger.isDebugEnabled()) {
            logger.debug("delete request " + deleterRequest);
        }
        int value = logManager.deleteLogs(deleterRequest.topic,deleterRequest.password);
        return new IntegersSend(value);
    }

}
