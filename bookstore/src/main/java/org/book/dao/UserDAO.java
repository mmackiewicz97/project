package org.book.dao;

import org.book.entity.User;

public interface UserDAO {
    void saveUser(User user);
    User getUser(String userId);
    User getUserName (String username);
}
