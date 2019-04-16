package pl.oskarpolak.blogapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {
    private Blog blog;

    @BeforeEach
    void setUp() {
        blog = new Blog();
    }



    @Test
    void shouldAddNewPostToPostList() {
        //given (mockito)
        int userId = 1;
        int postId = 1;
        String content = "Java jest super! :) ";
        User user = new User();
        user.setId(userId);


        //when
        blog.addUser(user);
        blog.publishPost(postId, userId, content);

        //then
        assertDoesNotThrow(() -> blog.findPostById(postId));
    }



    @Test
    void commentPost() {
        //given (mockito)
        int userId = 1;
        int postId = 1;
        String content = "Java jest super! :) ";
        User user = new User();
        user.setId(userId);


        //when
        blog.addUser(user);
        blog.publishPost(postId, userId, content);
        blog.commentPost(postId, userId, "SUPER KOMENETARZ! : )");

        //then
        assertFalse(blog.findPostById(postId).getComments().isEmpty());
    }

    @Test
    void displayUserEntries() {
        //given (mockito)
        int userId = 1;
        int postId = 1;
        String content = "Java jest super! :) ";
        User user = new User();
        user.setId(userId);


        //when
        blog.addUser(user);
        blog.publishPost(postId, userId, content);

        //then
        assertTrue(blog.displayUserEntries(userId));
    }
}