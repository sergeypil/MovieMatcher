package net.serg.movie_matcher_command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.serg.movie_matcher_command.event.BaseEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieEventProducer implements EventProducer {

    private final SnsClient snsClient; 

    @Value("${aws.sns.topic-arn}")
    private String topicArn;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void produce(BaseEvent event) {
        try {
            String message = objectMapper.writeValueAsString(event);
            
            PublishRequest request = PublishRequest.builder()
                                                   .topicArn(topicArn)
                                                   .message(message)
                                                   .build();
            
            PublishResponse response = snsClient.publish(request);
            
            log.info("Event published to SNS. MessageId: {}", response.messageId());
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize event to JSON: {}", event, e);
        } catch (Exception e) {
            log.error("Failed to publish event to SNS: {}", event, e);
        }
    }
}