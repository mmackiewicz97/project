package org.book.services;

import org.book.entity.User;

public interface UserService {
    void saveUser(User user);
    User getUser(String userId);
    User findByUsername(String name);
}
