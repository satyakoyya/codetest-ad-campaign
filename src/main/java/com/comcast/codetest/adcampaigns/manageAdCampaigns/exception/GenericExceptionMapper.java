package com.comcast.codetest.adcampaigns.manageAdCampaigns.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.comcast.codetest.adcampaigns.manageAdCampaigns.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage msg = new ErrorMessage(ex.getMessage(), 404);
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(msg)
				.build();
	}
}
