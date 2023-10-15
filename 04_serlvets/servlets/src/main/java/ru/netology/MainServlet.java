package ru.netology;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import ru.netology.configuration.MainConfig;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepositoryImpl;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;

public class MainServlet {
  public static void main(String[] args) throws LifecycleException, IOException {
    final var tomcat = new Tomcat();
    final var baseDir = Files.createTempDirectory("tomcat");
    baseDir.toFile().deleteOnExit();
    tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

    final var connector = new Connector();
    connector.setPort(8080);
    tomcat.setConnector(connector);

    tomcat.getHost().setAppBase(".");
    tomcat.addWebapp("", ".");

    tomcat.start();
    tomcat.getServer().await();
  }
//  @Override
//  protected void service(HttpServletRequest req, HttpServletResponse resp) {
//    // если деплоились в root context, то достаточно этого
//    try {
//      final var path = req.getRequestURI();
//      final var method = req.getMethod();
//      // primitive routing
//      if (method.equals("GET") && path.equals("/api/posts")) {
//        controller.all(resp);
//        return;
//      }
//      if (method.equals("GET") && path.matches("/api/posts/\\d+")) {
//        // easy way
//        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
//        controller.getById(id, resp);
//        return;
//      }
//      if (method.equals("POST") && path.equals("/api/posts")) {
//        controller.save(req.getReader(), resp);
//        return;
//      }
//      if (method.equals("DELETE") && path.matches("/api/posts/\\d+")) {
//        // easy way
//        final var id = Long.parseLong(path.substring(path.lastIndexOf("/")));
//        controller.removeById(id, resp);
//        return;
//      }
//      resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//    } catch (Exception e) {
//      e.printStackTrace();
//      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    }
//  }
}

