package org.book.controller;

import org.book.entity.Autor;
import org.book.entity.Category;
import org.book.services.AutorService;
import org.book.services.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AutorController {

    private AutorService autorService;
    @Autowired
    public void setAutorService(AutorService autorService){
        this.autorService = autorService;
    }

    @GetMapping("/")
    public String listAutors(Model model)
    {
        List<Autor> autors = autorService.getAutors();
        model.addAttribute("autors", autors);
        return "authors/list";
    }
    @GetMapping("/form")
    public String addForm(Model model)
    {
      Autor autor = new Autor();
        model.addAttribute("autor", autor);
        return "authors/addform";
    }
    @GetMapping("/edit")
    public String edit(@RequestParam("id") int id, Model model)
    {
        Autor autor = autorService.getAutor(id);
        model.addAttribute("autor", autor);
        return "authors/addform";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        autorService.deleteAutor(id);
        return "redirect:";
    }
    @PostMapping("/form")
    public String saveAutor(@ModelAttribute("autor") Autor autor)
    {
        autorService.saveAutor(autor);
        return "redirect:";
    }
}