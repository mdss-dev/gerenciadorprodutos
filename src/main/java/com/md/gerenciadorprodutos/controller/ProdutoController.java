package com.md.gerenciadorprodutos.controller;

import com.md.gerenciadorprodutos.model.Produto;
import com.md.gerenciadorprodutos.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProdutoController {
private final ProdutoService produtoService;

    // Acessa o formulario
    @GetMapping("/form")
    public String produtosForm(@Valid Produto produto){
        return "addProdutoForm";
    }

    // Adiciona novo produto
    @PostMapping("/add")
    public String novo(@Valid Produto produto, BindingResult result){
        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }
        produtoService.create(produto);
        return "redirect:/listar";
    }
    @GetMapping("/listar")
    public String lista(Model model) {
        List<Produto> produtos = produtoService.buscarTodos();
        model.addAttribute("produtos", produtos);
        return "todosprodutos";
    }


    // Acessa o formulario de edição
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") int id) {
        Produto produto = produtoService.buscarPorId(id);
        model.addAttribute("produto", produto);
        return "atualizaForm";
    }

    // Busca por categoria

    @GetMapping("/categoria")
    public String categoria(@RequestParam String categoria, Model model){
        model.addAttribute("produtos", produtoService.categoria(categoria));
        return "categoria";
    }

    @GetMapping("/buscarPorNome")
    public ModelAndView buscarPorNome(String nome){ModelAndView mv = new ModelAndView("/buscarPorNome");
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        if (produtos.isEmpty()) {
            mv.addObject("mensagem", "Nenhum produto encontrado com o nome: ");
        } else {
            mv.addObject("produtos", produtos);
        }
        return mv;
    }

    @PostMapping("update/{id}")
    public String alterarProduto(@Valid Produto produto, BindingResult result){
        if (result.hasErrors()) {
            return "redirect:/form";
        }
        produtoService.update(produto);
        return "redirect:/listar";
    }
    @GetMapping("delete/{id}")
    @CacheEvict(value = "produtos", allEntries = true)
    public String delete(@PathVariable(name = "id") int id, Model model) {
        produtoService.delete(id);
        return "redirect:/listar";
    }

    }
