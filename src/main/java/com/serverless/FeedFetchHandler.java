package com.serverless;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class FeedFetchHandler implements RequestHandler<Map<String, Object>, ApiGatewayResponse> {

	private static final Logger LOG = LogManager.getLogger(FeedFetchHandler.class);
	private static final FeedFetchFunction FUNCTION = new FeedFetchFunction();

	@Override
	public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {
		LOG.info("received: {}", input);

		FeedFetchResponse response = null;

		try {
			final ObjectMapper mapper = new ObjectMapper();
			response = FUNCTION.apply(mapper.readValue(input.get("body").toString(), FeedFetchRequest.class));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ApiGatewayResponse.builder()
				.setStatusCode(200)
				.setObjectBody(response)
				.setHeaders(Collections.singletonMap("X-Powered-By", "AWS Lambda & serverless"))
				.build();
	}
}
