package net.serg.movie_matcher_query.repository;

import net.serg.movie_matcher_query.entity.MovieQuery;
import org.springframework.stereotype.Repository;

@Repository
public class MovieQueryRepository {
    private final DynamoDbEnhancedClient dynamoDbClient;

    public MovieQueryRepository(DynamoDbEnhancedClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(MovieQuery movie) {
        DynamoDbTable<MovieQuery> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieQuery.class));
        table.putItem(movie);
    }
}