package br.com.projetosdio.controledeusuario.controledeusuario.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomePageController {
    @GetMapping
    public String homePage(){
        return "This is an API for people management";
    }
}
