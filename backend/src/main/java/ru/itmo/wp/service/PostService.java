package ru.itmo.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.repository.PostRepository;

import javax.validation.constraints.Null;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final JwtService jwtService;

    @Autowired
    public PostService(PostRepository postRepository, JwtService jwtService) {
        this.postRepository = postRepository;
        this.jwtService = jwtService;
    }
    public Post findById(long id) {
        return postRepository.findById(id).orElse(null);
    }
    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post createPost(PostForm postForm) {
        return postRepository.save(postForm.mapToPost(jwtService::find));
    }
}
