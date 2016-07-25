package com.comcast.codetest.adcampaigns.manageAdCampaigns.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.ErrorMessage;

@Provider
public class AdCampaignActiveExceptionMapper implements ExceptionMapper<AdCampaignActiveException>{

	@Override
	public Response toResponse(AdCampaignActiveException ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage(), 409);
		return Response.status(Status.CONFLICT)
				.entity(msg)
				.build();
	}
}
