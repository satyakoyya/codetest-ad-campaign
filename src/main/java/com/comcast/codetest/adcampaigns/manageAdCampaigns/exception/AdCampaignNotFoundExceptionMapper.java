package com.comcast.codetest.adcampaigns.manageAdCampaigns.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.ErrorMessage;

@Provider
public class AdCampaignNotFoundExceptionMapper implements ExceptionMapper<AdCampaignNotFoundException>{

	@Override
	public Response toResponse(AdCampaignNotFoundException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage(), 404);
		return Response.status(Status.NOT_FOUND)
				.entity(msg)
				.build();
	}

}
