package net.serg.movie_matcher_query.repository;

import net.serg.movie_matcher_query.entity.MovieEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.ArrayList;
import java.util.List;
@Repository
public class MovieRepository {
    private final DynamoDbEnhancedClient dynamoDbClient;

    public MovieRepository(DynamoDbEnhancedClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
    
    public void save(MovieEntity movie) {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        table.putItem(movie);
    }
    
    public void deleteById(String id) {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        table.deleteItem(MovieEntity.builder().id(id).build());
    }
    
    public List<MovieEntity> findAll() {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        List<MovieEntity> movies = new ArrayList<>();
        table.scan().items().forEach(movies::add);
        return movies;
    }
    
    public MovieEntity findById(String id) {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        return table.getItem(MovieEntity.builder().id(id).build());
    }
    
    public List<MovieEntity> findByGenre(String genre) {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        SdkIterable<Page<MovieEntity>> pages = table.index("GenreIndex")
                                                    .query(r -> r.queryConditional(
                                                        QueryConditional.keyEqualTo(k -> k.partitionValue(genre))));
        return extractItems(pages);
    }
    
    public List<MovieEntity> findByReleaseYear(int year) {
        DynamoDbTable<MovieEntity> table = dynamoDbClient.table("movies", TableSchema.fromBean(MovieEntity.class));
        SdkIterable<Page<MovieEntity>> pages = table.index("ReleaseYearIndex")
                                                    .query(r -> r.queryConditional(
                                                        QueryConditional.keyEqualTo(k -> k.partitionValue(year))));
        return extractItems(pages);
    }

    private <T> List<T> extractItems(SdkIterable<Page<T>> pages) {
        List<T> items = new ArrayList<>();
        pages.forEach(page -> page.items().forEach(items::add));
        return items;
    }
}