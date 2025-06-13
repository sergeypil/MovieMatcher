package net.serg.movie_matcher_command.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConfig {

    @Bean
    public SnsClient snsClient() {
        AwsCredentialsProvider credentialsProvider = DefaultCredentialsProvider.create();
        
        return SnsClient.builder()
                        .credentialsProvider(credentialsProvider)
                        .region(Region.US_EAST_1)
                        .build();
    }
}