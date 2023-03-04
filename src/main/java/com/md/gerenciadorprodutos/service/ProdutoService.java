package com.md.gerenciadorprodutos.service;

import com.md.gerenciadorprodutos.model.CategoriaProduto;
import com.md.gerenciadorprodutos.model.Produto;
import com.md.gerenciadorprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

public Produto create(Produto produto){
    return produtoRepository.save(produto);
}

public Produto update(Produto produto){
    produtoRepository.findById(produto.getId())
            .orElseThrow(() -> new IllegalArgumentException("Produto com Id: "+ produto.getId() +" não encontrado"));
    return produtoRepository.save(produto);
}

public List<Produto> categoria(String categoria){
    CategoriaProduto categoriaProduto = CategoriaProduto.valueOf(categoria.toUpperCase());
    List<Produto> produtos = produtoRepository.findByCategoria(categoriaProduto);
    return produtos;
}

public Optional<Produto> buscarPorId (int id){
   return produtoRepository.findById(id);
}

public List<Produto> buscarPorNome(String nome) {
    List<Produto> produtosEncontrados = produtoRepository.findByNomeContainingIgnoreCase(nome);
    if(produtosEncontrados.isEmpty()) {
        Collections.emptyList();
    }
    return produtosEncontrados;
}

public List<Produto> buscarTodos(){
    List<Produto> produtos = produtoRepository.findAll();
    return produtos;
}


public Produto delete(int id){
    Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    produtoRepository.delete(produto);
    return produto;
}
}
