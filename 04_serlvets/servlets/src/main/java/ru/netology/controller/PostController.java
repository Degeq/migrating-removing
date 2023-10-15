package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;

@RestController
@RequestMapping("/api/posts")
public class PostController {
  public static final String APPLICATION_JSON = "application/json";
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public Collection<Post> all() throws IOException {
    return service.all();
  }

  @GetMapping("{/id}")
  public Post getById(@PathVariable long id) throws IOException {
    // TODO: deserialize request & serialize response
    return service.getById(id);
  }

  @PostMapping()
  public Post save(@RequestBody Post post) throws IOException {
    return service.save(post);
  }

  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) throws IOException {
    // TODO: deserialize request & serialize response
    service.removeById(id);
  }
}
