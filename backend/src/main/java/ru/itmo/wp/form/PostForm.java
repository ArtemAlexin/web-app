package ru.itmo.wp.form;

import org.jetbrains.annotations.NotNull;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.function.Function;

public class PostForm {
    @Size(min = 1, max = 10000)
    private String text;

    @Size(min = 1, max = 100)
    private String title;

    @NotEmpty
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }
    public Post mapToPost(Function<String, User> lookingUpUserFunction) {
        Post post = new Post();
        post.setText(text);
        post.setTitle(title);
        post.setUser(lookingUpUserFunction.apply(jwt));
        return post;
    }
}
