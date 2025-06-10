package net.serg.movie_matcher_query.entity;

@DynamoDbBean
public class MovieQuery {
    private String id;
    private String title;
    private String genre;
    private double averageRating;

    // Getters and setters
}