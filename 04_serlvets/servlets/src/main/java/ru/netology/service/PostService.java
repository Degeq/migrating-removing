package ru.netology.service;

import org.springframework.stereotype.Service;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostAdvanced;
import ru.netology.repository.PostRepository;
import ru.netology.repository.PostRepositoryImpl;

import java.util.ArrayList;
import java.util.List;


public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    List<PostAdvanced> allList = repository.all();
    List<Post> externalList = new ArrayList<>();
    for (PostAdvanced i : allList) {
      externalList.add(i.getPost());
    }
    return externalList;
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }

  public Post save(Post post) {
    return repository.save(post);
  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

