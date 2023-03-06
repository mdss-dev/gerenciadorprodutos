package com.md.gerenciadorprodutos.controller;

import com.md.gerenciadorprodutos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = {"/","/home"})
    public String home() {
        return "home";
    }
}
