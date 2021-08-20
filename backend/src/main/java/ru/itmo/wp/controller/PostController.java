package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.service.PostService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class PostController {
    private final PostService postService;
    private final Validator validator;
    public PostController(PostService postService,
                          @Qualifier("postFormValidator") Validator validator) {
        this.postService = postService;
        this.validator = validator;
    }
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
    }
    @GetMapping("/posts")
    public List<Post> findPosts() {
        return postService.findAll();
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody @Valid PostForm postForm, BindingResult result) {
        if(result.hasErrors()) {
            throw new ValidationException(result);
        }
        postService.createPost(postForm);
    }
    @GetMapping("/post/{id:^[0-9]{1,18}$}")
    public Post getPostById(@PathVariable String id) {
        return postService.findById(Long.parseLong(id));
    }
}
