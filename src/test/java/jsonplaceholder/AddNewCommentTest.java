package jsonplaceholder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import model.Comment;
import org.junit.Test;

import java.util.Collections;

import static data.Path.COMMENTS;
import static data.Path.POSTS;
import static data.Uri.JSONPLACEHOLDER;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.path.json.JsonPath.from;

public class AddNewCommentTest {

    @Test
    public void addNewComment() {
        baseURI = JSONPLACEHOLDER.getUri();

        String name = "Sample comment name";
        String email = "sample@email.com";
        String body = "Sample comment body";

        int maxUserId = getMaxUserIdValue();

        int maxPostId = getMaxPostIdValueForUserId(maxUserId);

        addComment(maxPostId, name, email, body);
    }

    private int getMaxUserIdValue() {
        Response response = get(POSTS.getPath());

        return getMaxParameterValueFromResponse(response, "userId");
    }

    private int getMaxPostIdValueForUserId(int userId) {
        Response response = given()
                .param("userId", userId)
                .get(POSTS.getPath());

        return getMaxParameterValueFromResponse(response, "id");
    }

    private int getMaxParameterValueFromResponse(Response response, String parameterName) {
        return Collections.max(from(response.asString()).get(parameterName));
    }

    private void addComment(int postId, String name, String email, String body) {
        given()
                .contentType(JSON)
                .body(buildCommentAsJson(postId, name, email, body))
                .when()
                .post(COMMENTS.getPath())
                .then()
                .statusCode(201);
    }

    private String buildCommentAsJson(int postId, String name, String email, String body) {
        ObjectMapper mapper = new ObjectMapper();

        Comment comment = new Comment(postId, name, email, body);

        String json = null;
        try {
            return mapper.writeValueAsString(comment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }
}