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
package com.cloud.agent.api.to;

import java.util.ArrayList;
import java.util.List;

import com.cloud.network.rules.FirewallRule;
import com.cloud.network.rules.FirewallRule.State;
import com.cloud.utils.net.NetUtils;
import org.apache.cloudstack.api.InternalIdentity;

/**
 * FirewallRuleTO transfers a port range for an ip to be opened.
 *
 * There are essentially three states transferred with each state.
 *      sent multiple times to the destination.  If the rule is not on
 *   2. alreadyAdded - the rule has been successfully added before.  Rules
 *      in this state are sent for completeness and optimization.
 *      If the rule already exists on the destination, the destination should
 *      reply the rule is successfully applied.
 *
 *   - srcPortRange: port range to open.
 *   - protocol: protocol to open for.  Usually tcp and udp.
 *
 */
public class FirewallRuleTO implements InternalIdentity {
    long id;
    String srcVlanTag;
    String srcIp;
    String protocol;
    int[] srcPortRange;
    boolean revoked;
    boolean alreadyAdded;
    private List<String> sourceCidrList;
    FirewallRule.Purpose purpose;
    private Integer icmpType;
    private Integer icmpCode;


    protected FirewallRuleTO() {
    }

    public FirewallRuleTO(long id, String srcIp, String protocol, Integer srcPortStart, Integer srcPortEnd, boolean revoked, boolean alreadyAdded, FirewallRule.Purpose purpose, List<String> sourceCidr,Integer icmpType,Integer icmpCode) {
       this(id,null,srcIp,protocol,srcPortStart,srcPortEnd,revoked,alreadyAdded,purpose,sourceCidr,icmpType,icmpCode);
    }
    public FirewallRuleTO(long id,String srcVlanTag, String srcIp, String protocol, Integer srcPortStart, Integer srcPortEnd, boolean revoked, boolean alreadyAdded, FirewallRule.Purpose purpose, List<String> sourceCidr,Integer icmpType,Integer icmpCode) {
        this.id = id;
        this.srcVlanTag = srcVlanTag;
        this.srcIp = srcIp;
        this.protocol = protocol;

        if (srcPortStart != null) {
            List<Integer> portRange = new ArrayList<Integer>();
            portRange.add(srcPortStart);
            if (srcPortEnd != null) {
                portRange.add(srcPortEnd);
            }

            srcPortRange = new int[portRange.size()];
            int i = 0;
            for (Integer port : portRange) {
                srcPortRange[i] = port.intValue();
                i ++;
            }
        }

        this.revoked = revoked;
        this.alreadyAdded = alreadyAdded;
        this.purpose = purpose;
        this.sourceCidrList = sourceCidr;
        this.icmpType = icmpType;
        this.icmpCode = icmpCode;
    }
    public FirewallRuleTO(FirewallRule rule, String srcVlanTag, String srcIp) {
        this(rule.getId(),srcVlanTag, srcIp, rule.getProtocol(), rule.getSourcePortStart(), rule.getSourcePortEnd(), rule.getState()==State.Revoke, rule.getState()==State.Active, rule.getPurpose(),rule.getSourceCidrList(),rule.getIcmpType(),rule.getIcmpCode());
    }

    public FirewallRuleTO(FirewallRule rule, String srcIp) {
        this(rule.getId(),null, srcIp, rule.getProtocol(), rule.getSourcePortStart(), rule.getSourcePortEnd(), rule.getState()==State.Revoke, rule.getState()==State.Active, rule.getPurpose(),rule.getSourceCidrList(),rule.getIcmpType(),rule.getIcmpCode());
    }

    public long getId() {
        return id;
    }

    public String getSrcVlanTag() {
    	return srcVlanTag;
    }

    public String getSrcIp() {
        return srcIp;
    }

    public String getProtocol() {
        return protocol;
    }

    public int[] getSrcPortRange() {
        return srcPortRange;
    }

    public Integer getIcmpType(){
    	return icmpType;
    }

    public Integer getIcmpCode(){
    	return icmpCode;
    }

    public String getStringSrcPortRange() {
    	if (srcPortRange == null || srcPortRange.length < 2)
    		return "0:0";
    	else
    		return NetUtils.portRangeToString(srcPortRange);
    }

    public boolean revoked() {
        return revoked;
    }

    public List<String> getSourceCidrList() {
        return sourceCidrList;
    }

    public boolean isAlreadyAdded() {
        return alreadyAdded;
    }

    public FirewallRule.Purpose getPurpose() {
        return purpose;
    }

}
