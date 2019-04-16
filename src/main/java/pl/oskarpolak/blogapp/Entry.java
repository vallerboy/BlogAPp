package pl.oskarpolak.blogapp;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Entry {
    private int id;
    private LocalDateTime postDate;
    private User author;
    private String content;
}
