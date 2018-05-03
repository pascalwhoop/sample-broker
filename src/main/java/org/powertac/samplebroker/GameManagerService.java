/*
 *  Copyright 2009-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an
 *  "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied. See the License for the specific language
 *  governing permissions and limitations under the License.
 */

package org.powertac.samplebroker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.powertac.common.msg.SimPause;
import org.powertac.common.msg.SimResume;
import org.powertac.common.msg.TimeslotComplete;
import org.powertac.common.msg.TimeslotUpdate;
import org.powertac.grpc.GrpcServiceChannel;
import org.powertac.samplebroker.interfaces.BrokerContext;
import org.powertac.samplebroker.interfaces.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * additional class that is used for the GRPC adapter to get a hold of all the extra interesting messages that the sample-broker doesn't usually hand out to the developers
 */
public class GameManagerService implements Initializable
{
  static private Logger log = LogManager.getLogger(ContextManagerService.class);
  @Autowired
  GrpcServiceChannel comm;

  @Override
  public void initialize(BrokerContext broker){

  }

  public synchronized void handleMessage(SimPause message){
    comm.gameStub.handlePBSimPause(comm.converter.convert(message));

  }
  public synchronized void handleMessage(SimResume message){
    comm.gameStub.handlePBSimResume(comm.converter.convert(message));
  }
  public synchronized void handleMessage(TimeslotUpdate message){
    comm.gameStub.handlePBTimeslotUpdate(comm.converter.convert(message));
  }
  public synchronized void handleMessage(TimeslotComplete message){
    comm.gameStub.handlePBTimeslotComplete(comm.converter.convert(message));
  }
}
