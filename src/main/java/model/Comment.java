package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Comment {
    int postId;
    String name;
    String email;
    String body;

    @JsonCreator
    public Comment(
            @JsonProperty("postId") int postId,
            @JsonProperty("name") String name,
            @JsonProperty("email") String email,
            @JsonProperty("body") String body) {
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
