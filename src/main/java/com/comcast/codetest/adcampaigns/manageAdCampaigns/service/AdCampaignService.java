package com.comcast.codetest.adcampaigns.manageAdCampaigns.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.db.InMemoryDB;
import com.comcast.codetest.adcampaigns.manageAdCampaigns.exception.AdCampaignActiveException;
import com.comcast.codetest.adcampaigns.manageAdCampaigns.exception.AdCampaignNotFoundException;
import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.AdCampaign;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

/**
 * @author Satya
 *
 */
public class AdCampaignService {
	
	private ExpiringMap<String, AdCampaign> campaigns = (ExpiringMap<String, AdCampaign>) InMemoryDB.getCampaigns();
	Integer i = 0;
	
	public List<AdCampaign> getAllCampaigns(){
		return new ArrayList<AdCampaign>(campaigns.values());
	}
	
	public AdCampaign getCampaign(String id) {
		AdCampaign ac = campaigns.get(id);
		if (null == ac) throw new AdCampaignNotFoundException("An active AdCampaign with partner ID " + id + " does  not exist.");
		return ac;
	}
	
	public AdCampaign addCampaign(AdCampaign ac) throws Exception{
		isActiveAdCampaign(ac);
		if (null != ac) {
			if (null != ac.getPartner_id() ){
				ac.setPartner_id(ac.getPartner_id());
				campaigns.put(ac.getPartner_id(), ac, ExpirationPolicy.CREATED, ac.getDuration(), TimeUnit.SECONDS);
			}else {
				//i = (int) (Math.random()*100);
				ac.setPartner_id("cmp"+String.valueOf((int) (Math.random()*100)));
				isActiveAdCampaign(ac);
				if (null != ac) campaigns.put(ac.getPartner_id(), ac, ExpirationPolicy.CREATED, ac.getDuration(), TimeUnit.SECONDS);
			}	
		}
		return ac;
	}
	
	public boolean isActiveAdCampaign(AdCampaign ac) throws Exception {
		boolean isActiveCampaign = false;
		Set<String> partnerIdSet = (Set<String>) campaigns.keySet();
		if(partnerIdSet.contains(ac.getPartner_id())){
			isActiveCampaign = true;
			throw new AdCampaignActiveException("An active AdCampaign with partner ID " + ac.getPartner_id() + 
					" already exist.");
			}
		return isActiveCampaign;
	}
	
	public AdCampaign updateCampaign(AdCampaign ac) throws Exception {
		Set<String> partnerIdSet = (Set<String>) campaigns.keySet();
		if (null != ac) {
			if (partnerIdSet.contains(ac.getPartner_id())) campaigns.remove(ac.getPartner_id());
			ac.setPartner_id(ac.getPartner_id());
			campaigns.put(ac.getPartner_id(), ac, ExpirationPolicy.CREATED, ac.getDuration(), TimeUnit.SECONDS);
		}
		return ac;
	}
	
	public AdCampaign removeCampaign(String id) {
		return campaigns.remove(id);
	}
	
	
}
