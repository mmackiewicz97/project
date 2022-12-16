package org.book.services;

import org.book.entity.Book;
import org.book.entity.Category;

import java.util.List;

public interface CategorySevice {
    List<Category> getCategories();
    void saveCategory(Category category);
    Category getCategory(int categoryId);
    void deleteCategory(int categoryId);
}
