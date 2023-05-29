package com.example.contentApp.controller;

import com.example.contentApp.model.Content;
import com.example.contentApp.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    private ContentCollectionRepository contentCollectionRepository;

    public ContentController(ContentCollectionRepository repository) {
        this.contentCollectionRepository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Content> getAllContent() {
        return contentCollectionRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Content getContentById(@PathVariable Integer id) {
        return contentCollectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void postNewContent(@Valid @RequestBody Content content) {
        contentCollectionRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping
    public void updatedExistingContent(@Valid @RequestBody Content content) {

        if(contentCollectionRepository.findById(content.id()).isPresent()) {
            contentCollectionRepository.deleteById(content.id());
            contentCollectionRepository.save(content);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot update - content not found!");
        }
    }

}
