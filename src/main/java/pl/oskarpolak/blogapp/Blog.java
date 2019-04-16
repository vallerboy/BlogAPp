package pl.oskarpolak.blogapp;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Blog {
    private List<Post> posts;
    private List<User> users;


    public Blog(){
        posts = new ArrayList<>();
        users = new ArrayList<>();
    }


    public void publishPost(int postId, int userId, String content){
        if(!isPostIdUnique(postId)){
            throw new IllegalStateException("PostId is already in use");
        }

        User foundUser  = findUserByUserId(userId);
        Post post = new Post(postId, foundUser, content);

        posts.add(post);
    }

    private boolean isPostIdUnique(int postId) {
        return posts.stream().noneMatch(post -> post.getId() == postId);
    }

    public void addUser(User user){
        users.add(user);
    }

    public void commentPost(int postId, int userId, String content){
        Post post = findPostById(postId);
        User user = findUserByUserId(userId);

        Comment newComment  = new Comment();
        newComment.setAuthor(user);
        newComment.setContent(content);
        newComment.setPostDate(LocalDateTime.now());


        post.addComment(newComment);
    }

    public boolean displayUserEntries(int userId){
        boolean foundSomething = false;

        for (Post post : posts) {
            if(post.getAuthor().getId() == userId){
                System.out.println("[POST] - " + post.getContent());
                foundSomething = true;
            }

            for (Comment comment : post.getComments()) {
                 if(comment.getAuthor().getId() == userId){
                     System.out.println("[COMMENT] - " + comment.getContent());
                     foundSomething = true;
                 }
            }
        }
        return foundSomething;
    }

    /**
     * Metoda szukająca zalogowanego użytkownika po jego ID
     * @param userId
     * @return
     */
    public User findUserByUserId(int userId){
        return users
                .stream()
                .filter(user -> user.getId() == userId) //iteruje po każdym elemencie zbioru users
                .findAny() //nawet jak bedzie wiecej, to wyciagnie 1 (w naszej logice biznesowej, zawsze bedzie to tylko 1 gosciu)
                .orElseThrow(() -> new IllegalStateException("User with this id not exist"));
//        for (User user : users) {
//            if(user.getId() == userId){
//                return user;
//            }
//        }
//
//        throw new IllegalStateException("");
    }

    public Post findPostById(int postId){
        return posts
                .stream()
                .filter(post -> post.getId() == postId) //iteruje po każdym elemencie zbioru users
                .findAny() //nawet jak bedzie wiecej, to wyciagnie 1 (w naszej logice biznesowej, zawsze bedzie to tylko 1 gosciu)
                .orElseThrow(() -> new IllegalStateException("Post with this id not exist"));
    }
}
