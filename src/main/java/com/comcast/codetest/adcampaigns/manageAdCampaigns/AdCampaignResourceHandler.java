/**
 * 
 */
package com.comcast.codetest.adcampaigns.manageAdCampaigns;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.AdCampaign;
import com.comcast.codetest.adcampaigns.manageAdCampaigns.service.AdCampaignService;

/**
 * @author Satya
 *
 */

@Path("/ad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdCampaignResourceHandler {
	
	AdCampaignService acService = new AdCampaignService();

	@GET
	public List<AdCampaign> getCampaign(){
		return acService.getAllCampaigns();
	}

	@GET
	@Path("/{partner_id}")
	public AdCampaign getCampaignById(@PathParam("partner_id") String partner_id){
		return acService.getCampaign(partner_id);
	}
	
	@POST

	public AdCampaign createCampaign(AdCampaign adcampaign) throws Exception{
		return acService.addCampaign(adcampaign);
	}
	
	@PUT
	@Path("/{partner_id}")
	public AdCampaign updateCampaign(@PathParam("partner_id") String partner_id, AdCampaign adcampaign) throws Exception{
		adcampaign.setPartner_id(partner_id);
		return acService.updateCampaign(adcampaign);
	}
	
	@DELETE
	@Path("/{partner_id}")
	public AdCampaign deleteCampaign(@PathParam("partner_id") String partner_id){
		return acService.removeCampaign(partner_id);
	}
	
}
