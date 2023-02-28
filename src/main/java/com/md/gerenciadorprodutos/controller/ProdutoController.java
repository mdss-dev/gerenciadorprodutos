package com.md.gerenciadorprodutos.controller;

import com.md.gerenciadorprodutos.model.Produto;
import com.md.gerenciadorprodutos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Acessa o formulario
    @GetMapping("/form")
    public String produtosForm(Produto produto){
        return "addProdutoForm";
    }

    // Adiciona novo produto
    @PostMapping("/add")
    public String novo(@Valid Produto produto, BindingResult result){
        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }
        produtoRepository.save(produto);
        return "redirect:/home";
    }

    // Acessa o formulario de edição
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") int id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto com Id: "+ id +" não encontrado"));
        model.addAttribute("produto", produto);
        return "atualizaForm";
    }

    // Atualiza produto
    @PostMapping("update/{id}")
    public String alterarProduto(@Valid Produto produto, BindingResult result, @PathVariable int id){
        if (result.hasErrors()) {
            return "redirect:/form";
        }
        produtoRepository.save(produto);
        return "redirect:/home";
    }
    // Exclui um produto
    @GetMapping("delete/{id}")
    @CacheEvict(value = "produtos", allEntries = true)
    public String delete(@PathVariable(name = "id") int id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto com Id: "+ id +" não encontrado"));
        produtoRepository.delete(produto);
        return "redirect:/home";
    }

    }
