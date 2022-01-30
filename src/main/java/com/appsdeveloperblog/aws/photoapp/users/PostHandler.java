package com.appsdeveloperblog.aws.photoapp.users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

/**
 * Handler for requests to Lambda function.
 */
public class PostHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input,
                                                      final Context context) {

        //TODO: Lambda Logger

        LambdaLogger logger = context.getLogger();
        logger.log("Handling HTTP request for the /users API endpoint\n");

        //TODO: Input body

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        String requestBody = input.getBody();
        Gson gson = new Gson();

        //TODO: Request Body

        Map<String, String> requestMap = gson.fromJson(requestBody, Map.class);
        String firstName = requestMap.get("firstName");
        String lastName = requestMap.get("lastName");
        String userId = UUID.randomUUID().toString();

        //TODO: Response Body
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("firstName", firstName);
        responseMap.put("lastName", lastName);
        responseMap.put("userId", userId);

        //TODO: Response Headers
        responseEvent.withHeaders(Map.of("Content-Type", "application-json"));

        //TODO: Response StatusCode
        responseEvent.withStatusCode(200);

        //TODO: Response With Body
        responseEvent.withBody(gson.toJson(responseMap, Map.class));

        return responseEvent;

    }


    /*public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent input, final Context context) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("X-Custom-Header", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);
        try {
            final String pageContents = this.getPageContents("https://checkip.amazonaws.com");
            String output = String.format("{ \"message\": \"hello world\", \"location\": \"%s\" }", pageContents);

            return response
                    .withStatusCode(200)
                    .withBody(output);
        } catch (IOException e) {
            return response
                    .withBody("{}")
                    .withStatusCode(500);
        }
    }*/

    /*private String getPageContents(String address) throws IOException{
        URL url = new URL(address);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return br.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }*/
}
