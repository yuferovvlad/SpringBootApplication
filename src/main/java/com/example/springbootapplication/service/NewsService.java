package com.example.springbootapplication.service;

import com.example.springbootapplication.exception.ResourceNotFoundException;
import com.example.springbootapplication.model.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final ConcurrentHashMap<Long, News> newsStorage = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    /**
     * Получение новости по её идентификатору
     */
    public News getById(long id) {
        News news = newsStorage.get(id);
        if (news == null) {
            throw new ResourceNotFoundException("News with id " + id + " not found");
        }
        return news;
    }

    /**
     * Получение списка всех новостей
     */
    public List<News> getAll() {
        return new ArrayList<>(newsStorage.values());
    }

    /**
     * Создание новой новости
     */
    public News create(News news) {
        long id = idCounter.incrementAndGet();
        news.setId(id);
        newsStorage.put(id, news);
        return news;
    }

    /**
     * Обновление существующей новости
     */
    public News update(News news) {
        if (!newsStorage.containsKey(news.getId())) {
            throw new ResourceNotFoundException("News with id " + news.getId() + " not found");
        }
        newsStorage.put(news.getId(), news);
        return news;
    }

    /**
     * Удаление новости по индентификатору
     */
    public void deleteById(long id) {
        News removed = newsStorage.remove(id);
        if (removed == null) {
            throw new ResourceNotFoundException("News with id " + id + " not found");
        }
    }
}
