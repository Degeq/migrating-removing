package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostAdvanced;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class PostRepositoryImpl implements PostRepository{

    private HashMap<Long, PostAdvanced> postContainer = new HashMap<>();
    private AtomicInteger postCounter = new AtomicInteger(1);

    public List<PostAdvanced> all() {
        return new ArrayList<>(postContainer.values()
                .stream()
                .filter(x -> x.isRemoved())
                .collect(Collectors.toList()));
    }

    public Optional<Post> getById(long id) {
        if (postContainer.containsKey(id)) {
            if(postContainer.get(id).isRemoved()){
                throw new NotFoundException("Пост с таким ID не найден");
            } else {
                return Optional.ofNullable(new Post(postContainer.get(id).getId(), postContainer.get(id).getContent()));
            }
        } else {
            throw new NotFoundException("Пост с таким ID не найден");
        }
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            synchronized (postCounter) {
                post.setId(postCounter.get());
                postContainer.put(Long.valueOf(postCounter.getAndIncrement()), new PostAdvanced(post.getId(), post.getContent()));
            }
        } else {
            if (postContainer.containsKey(post.getId()) && !postContainer.get(post.getId()).isRemoved()) {
                postContainer.put(post.getId(), new PostAdvanced(post.getId(), post.getContent()));
            } else {
                throw new NotFoundException("Пост с таким ID не найден");
            }
        }
        return post;
    }


    public void removeById(long id) {
        if (postContainer.containsKey(id) && !postContainer.get(id).isRemoved()) {
            postContainer.get(id).remove();
        } else {
            throw new NotFoundException("Пост с таким ID не найден");
        }
    }
}
