package com.javatechie.multiple.ds.api.controller;

import com.javatechie.multiple.ds.api.book.repository.BookRepository;
import com.javatechie.multiple.ds.api.model.book.Book;
import com.javatechie.multiple.ds.api.model.user.User;
import com.javatechie.multiple.ds.api.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/multipledb")
public class Controller {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        User save = userRepository.save(user);
        System.out.println("adding user : "+save);
        return save;
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        List<User> all = userRepository.findAll();
        System.out.println("users = " + all);
        return all;
    }

    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        Book save = bookRepository.save(book);
        System.out.println("adding book : "+save);
        return save;
    }

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        List<Book> all = bookRepository.findAll();
        System.out.println("Books = " + all);
        return all;
    }
}
