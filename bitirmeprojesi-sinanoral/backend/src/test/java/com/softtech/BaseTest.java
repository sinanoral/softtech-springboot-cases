package com.softtech;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softtech.model.responseDto.RestResponse;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

public class BaseTest {
    protected ObjectMapper objectMapper;

    protected boolean isSuccess(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        RestResponse restResponse = getRestResponse(result);
        return isSuccess(restResponse);
    }

    private RestResponse getRestResponse(MvcResult result) throws JsonProcessingException, UnsupportedEncodingException {
        return objectMapper.readValue(result.getResponse().getContentAsString(), RestResponse.class);
    }

    private boolean isSuccess(RestResponse restResponse) {
        return restResponse.isSuccess();
    }
}
