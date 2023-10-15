package ru.netology.repository;

import ru.netology.model.Post;
import ru.netology.model.PostAdvanced;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<PostAdvanced> all();

    Optional<Post> getById(long id);

    Post save(Post post);

    void removeById(long id);
}
