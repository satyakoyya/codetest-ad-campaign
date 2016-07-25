/**
 * 
 */
package com.comcast.codetest.adcampaigns.manageAdCampaigns.db;

import java.util.Map;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.AdCampaign;

import net.jodah.expiringmap.ExpiringMap;

/**
 * @author Satya
 *
 */
public class InMemoryDB {

	private static Map<String, AdCampaign>  adCampaigns = ExpiringMap.builder()
			.variableExpiration()
			.build();
	public static Map<String, AdCampaign> getCampaigns() {
		return adCampaigns;
	}

}
