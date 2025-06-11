package net.serg.movie_matcher_query.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import net.serg.movie_matcher_query.service.MovieMatcherEventsQueueListenerErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
@EnableJms
public class SqsConfig {

    @Value("${aws.region}")
    private String region;

    @Bean
    public SqsClient amazonSQS(AwsCredentialsProvider credentialsProvider) {
        return SqsClient
            .builder()
            .credentialsProvider(credentialsProvider)
            .region(Region.of(region))
            .build();
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        return DefaultCredentialsProvider.create();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
        SQSConnectionFactory sqsConnectionFactory,
        MovieMatcherEventsQueueListenerErrorHandler errorHandler) {

        var factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setErrorHandler(errorHandler);
        return factory;
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(SqsClient amazonSQS) {
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
    }
}