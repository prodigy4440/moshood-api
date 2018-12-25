package com.fahdisa.moshood.config;

import com.fahdisa.moshood.util.BaseResponse;
import com.fahdisa.moshood.util.Codes;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response
                .ok(new BaseResponse.Builder<>()
                        .setCode(Codes.NOT_FOUND_ERROR)
                        .setDescription(e.getMessage())
                        .build()
                ).build();
    }
}
