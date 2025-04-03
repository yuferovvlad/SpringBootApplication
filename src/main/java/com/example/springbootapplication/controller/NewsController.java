package com.example.springbootapplication.controller;

import com.example.springbootapplication.model.News;
import com.example.springbootapplication.service.NewsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public News getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public List<News> getAll() {
        return newsService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public News create(@RequestBody News news) {
        return newsService.create(news);
    }

    @PutMapping
    public News update(@RequestBody News news) {
        return newsService.update(news);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        newsService.deleteById(id);
    }
}
