package ru.itmo.wp.domain.DTO;

import ru.itmo.wp.domain.User;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserDTO {
    private long id;
    private String name;
    private String login;
    private Date creationTime;
    private boolean isAdmin;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.creationTime = user.getCreationTime();
        this.isAdmin = user.isAdmin();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
