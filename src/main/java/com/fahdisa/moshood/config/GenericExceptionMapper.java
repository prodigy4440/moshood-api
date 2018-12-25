package com.fahdisa.moshood.config;

import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        return Response
                .ok(new BaseResponse.Builder<>()
                        .setCode(Codes.GENERIC_ERROR)
                        .setDescription(e.getMessage())
                        .build()
                ).build();
    }
}
