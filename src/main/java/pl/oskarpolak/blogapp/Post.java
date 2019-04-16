package pl.oskarpolak.blogapp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Post extends Entry {
    private List<Comment> comments;

    public Post(int id, User author, String content){
        setId(id);
        setAuthor(author);
        setContent(content);

        comments = new ArrayList<>();
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }
}
