package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.model.RetentionSetting;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.syndicate.deployment.annotations.events.SnsEventSource;
import java.util.HashMap;
import java.util.Map;

@LambdaHandler(
    lambdaName = "sns_handler",
	roleName = "sns_handler-role",
	isPublishVersion = false,
	logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)

@SnsEventSource(
		targetTopic = "lambda_topic"
)
@DependsOn(
		name = "lambda_topic",
		resourceType=ResourceType.SNS_TOPIC
)
public class SnsHandler implements RequestHandler<SNSEvent, Map<String, Object>> {

	public Map<String, Object> handleRequest(SNSEvent event, Context context) {
		System.out.println("Hello from lambda");
		System.out.println("sns logs"+" "+event.getRecords());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("statusCode", 200);
		resultMap.put("body", "Hello from Lambda");
		return resultMap;
	}
}
