package com.example.contentApp.repository;

import com.example.contentApp.model.Content;
import com.example.contentApp.model.Status;
import com.example.contentApp.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {


    private List<Content> contentList = new ArrayList<>();

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }

    public void save(Content content) {
        contentList.add(content);
    }

    public void deleteById(Integer id) {
        contentList.removeIf(content -> content.id().equals(id));
    }


    @PostConstruct
    void initList() {
        Content content = new Content(1, "My first content", "This is my first clip I've created",
                Status.PUBLISHED, Type.MOVIE_CLIP, LocalDateTime.now(), null, "/1sClip");

        contentList.add(content);
    }

}
