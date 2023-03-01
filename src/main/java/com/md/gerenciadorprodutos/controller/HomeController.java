package com.md.gerenciadorprodutos.controller;

import com.md.gerenciadorprodutos.model.CategoriaProduto;
import com.md.gerenciadorprodutos.model.Produto;
import com.md.gerenciadorprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/home")
    public String home(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "home";
    }

    @GetMapping("/listar")
    public String lista(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "todosprodutos";
    }
    @GetMapping("/")
    public String categoria(@RequestParam String categoria, Model model) {
        CategoriaProduto categoriaProduto = CategoriaProduto.valueOf(categoria.toUpperCase());
        List<Produto> produtos = produtoRepository.findByCategoria(categoriaProduto);
        model.addAttribute("produtos", produtos);
        return "categoria";
    }

}
