package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class PostRepositoryImpl implements PostRepository{

    private HashMap<Long, Post> postContainer = new HashMap<>();
    private AtomicInteger postCounter = new AtomicInteger(1);

    public List<Post> all() {
        return new ArrayList<>(postContainer.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(postContainer.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            synchronized (postCounter) {
                post.setId(postCounter.get());
                postContainer.put(Long.valueOf(postCounter.getAndIncrement()), post);
            }
        } else {
            if (postContainer.containsKey(post.getId())) {
                postContainer.put(post.getId(), post);
            } else {
                throw new NotFoundException("Пост с таким ID не найден");
            }
        }
        return post;
    }


    public void removeById(long id) {
        if (postContainer.containsKey(id)) {
            postContainer.remove(id);
        } else {
            throw new NotFoundException("Пост с таким ID не найден");
        }
    }
}
