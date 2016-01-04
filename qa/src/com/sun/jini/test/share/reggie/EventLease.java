/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sun.jini.test.share.reggie;

import net.jini.core.lease.*;
import java.rmi.RemoteException;

/**
 * When a registrar (lookup service) grants a lease on an event registration
 * on behalf of some object (client), a proxy is employed to allow the client
 * to interact with the lease; this class is the implementation of that proxy.
 * Clients only see instances of this class via the Lease interface.
 *
 * @author Sun Microsystems, Inc.
 *
 */
class EventLease extends RegistrarLease {

    private static final long serialVersionUID = 1322660638288375494L;

    /**
     * The eventID returned in the EventRegistration.
     *
     * @serial
     */
    private final long eventID;

    /**
     * Constructs a lease, granted by a given registrar with a given
     * expiration time, for an event registration on behalf of a client.
     *
     * @param server object representing the registrar that grants the lease
     * @param eventID ID of the event registration upon which the lease is
     *        granted
     * @param leaseID ID of the lease, granted by the registrar, on the event
     *        registration
     * @param expiration the time of expiration of the lease being granted
     */
    EventLease(Registrar server, long eventID, long leaseID, long expiration) {
	super(server, leaseID, expiration);
	this.eventID = eventID;
    }

    // This method's javadoc is inherited from an interface of this class
    public void cancel() throws UnknownLeaseException, RemoteException {
	server.cancelEventLease(eventID, leaseID);
    }

    /** 
     * Renews the event lease associated with an instance of this class.
     * Each instance of this class corresponds to a lease on an event
     * registration for a particular client. This method renews that 
     * lease on behalf of the client.
     *
     * @param duration the requested duration for the lease being renewed
     * @return long value representing the new duration that was granted
     *         for the renewed lease. Note that the duration returned may
     *         be less than the duration requested.
     * @exception UnknownLeaseException indicates the lease does not exist;
     *            typically because the lease has expired.
     */
    protected long doRenew(long duration)
	throws UnknownLeaseException, RemoteException
    {
	return server.renewEventLease(eventID, leaseID, duration);
    }

    // This method's javadoc is inherited from a super class of this class
    Object getRegID() {
	return new Long(eventID);
    }

    /** 
     * Returns a hash code based on content.
     *
     * @return int value representing the hash code for an instance of this
     *         class.
     */
    public int hashCode() {
	return server.hashCode() ^ (int)eventID ^ (int)leaseID;
    }

    /** 
     * Two EventLease objects are defined to be equal if they satisfy
     * the following conditions:
     * <ul>
     *   <li> both objects were generated by the same registrar
     *   <li> the IDs of the leases associated with each object are equal (==)
     *   <li> each object's event registration IDs are equal (==)
     * </ul>
     */
    public boolean equals(Object obj) {
	if (!(obj instanceof EventLease))
	    return false;
	EventLease ls = (EventLease)obj;
	return (server.equals(ls.server) &&
		eventID == ls.eventID && leaseID == ls.leaseID);
    }
}