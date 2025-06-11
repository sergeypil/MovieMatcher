package net.serg.movie_matcher_query.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class MovieEntity extends BaseEntity  {

    private String id;         
    private String title;    
    private String genre;     
    private int releaseYear;   
    private String director;  
    
    @DynamoDbPartitionKey
    @DynamoDbAttribute("id") 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @DynamoDbAttribute("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "GenreIndex") 
    @DynamoDbAttribute("genre")
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "ReleaseYearIndex") 
    @DynamoDbAttribute("releaseYear")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @DynamoDbAttribute("director")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}