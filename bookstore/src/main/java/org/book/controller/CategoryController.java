package org.book.controller;

import org.book.entity.Autor;
import org.book.entity.Book;
import org.book.entity.Category;
import org.book.services.BookService;
import org.book.services.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategorySevice categorySevice;
    //@RequestMapping("/list")
    @GetMapping("/")
    public String listCategory(Model model)
    {
        List<Category> categories = categorySevice.getCategories();
        model.addAttribute("categories", categories);
        return "categories/list";
    }
    @GetMapping("/form")
    public String addForm(Model model)
    {
        Category category = new Category();
        model.addAttribute("category",category);
        return "categories/addform";
    }
    @PostMapping("/form")
    public String saveCategory(@ModelAttribute("category") Category category)
    {
        categorySevice.saveCategory(category);
        return "redirect:";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model)
    {
        Category category = categorySevice.getCategory(id);
        model.addAttribute("category", category);
        return "categories/addform";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        categorySevice.deleteCategory(id);
        return "redirect:";
    }
}