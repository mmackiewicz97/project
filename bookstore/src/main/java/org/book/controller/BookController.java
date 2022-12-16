package org.book.controller;

import com.mysql.cj.jdbc.Blob;
import org.book.entity.Book;
import org.book.services.AutorService;
import org.book.services.BookService;
import org.book.services.CategorySevice;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.MultipartConfig;

@Controller
@RequestMapping("/books")
@MultipartConfig
public class BookController {
    private BookService bookService;
    private CategorySevice categorySevice;
    private AutorService autorService;
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    @Autowired
    public void setCategorySevice(CategorySevice categorySevice) {
        this.categorySevice = categorySevice;
    }
    @Autowired
    public void setAutorService(AutorService autorService) {
        this.autorService = autorService;
    }

    //@RequestMapping("/list")
    @GetMapping("/")
    public String listBooks(Model model)
    {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books",books);
        return "books/bookslist";
    }
    @GetMapping("/info")
    public String listBook(@RequestParam("bookId") int id, Model model)
    {
        Book book = bookService.getBook(id);
        model.addAttribute("book",book);
        return "books/info";
    }
    @GetMapping("/form")
    public String addForm(Model model)
    {
        Book book = new Book();
        model.addAttribute("book",book);
        model.addAttribute("categories",categorySevice.getCategories());
        model.addAttribute("authors", autorService.getAutors());
        return "books/addform";
    }
//    @PostMapping(value = "/form", consumes = {"multipart/form-data"})
    @PostMapping(value = "/form")
//    public String saveBook(@ModelAttribute("book") Book ksiazka, @RequestParam("okladka") MultipartFile okladka)
    public String saveBook(@ModelAttribute("book") Book ksiazka, @RequestParam("okladka2") MultipartFile multipartImage)
    {
        Logger logger = LoggerFactory.getLogger(BookController.class);
        try {
            logger.info(multipartImage.getName());
            ksiazka.setFilename(multipartImage.getOriginalFilename());
            ksiazka.setOkladka(multipartImage.getBytes());
            ksiazka.setContentType(multipartImage.getContentType());
            bookService.saveBook(ksiazka);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("bookId") int bookId, Model model){
        Book book = bookService.getBook(bookId);
        model.addAttribute("book",book);
        model.addAttribute("categories",categorySevice.getCategories());
        model.addAttribute("authors", autorService.getAutors());
        return "books/addform";
    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int bookId) {
        bookService.deleteBook(bookId);
        return "redirect:";
    }
}
//TODO
//wyszukiwanie autorow w formularzu rozwijanym po nazwisku
//edytowanie
//usuwanie

//koszyk na 3,5, nie w bazie tylko w pamieci
//na 4: zamowienia, z koszyka z rolami user moze zlozyc, admin zmienic status
// adres przy userach
//na 4,5: platnosc, wyslanie maila uzytkownikowi ze szczegolami zamowienia i szczegolami do przelewu, ile zaplacic
//      w springu, lub w javie gotowe rozwiazanie spring-mail
//      platnos PayU, sandbox, mozna w grupach
//service sendmessage, pobierz koszyk i wyslij
//na 5 wyglad aplikacji, bootstrap, obramowanie tabel, do ksiazek okladki
//obrazki jako string w bazie, blob teraz w bazie, tablica baitow w javie

//wysylanie lepiej nie z gmaila
//2 osoby przy mailu, 3 z payu
