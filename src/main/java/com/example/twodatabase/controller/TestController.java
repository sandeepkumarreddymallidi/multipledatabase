package com.example.twodatabase.controller;

import com.example.twodatabase.db1.entities.UserEntity;
import com.example.twodatabase.db1.repository.UserRepository;
import com.example.twodatabase.db2.entities.BookEntity;
import com.example.twodatabase.db2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/addData")
    public String getData(){
        bookRepository.save(new BookEntity(1,"java"));
        userRepository.save(new UserEntity(1,"sandeep"));
    return "added data successfully";
    }
}
