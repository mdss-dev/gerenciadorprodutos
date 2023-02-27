package com.md.gerenciadorprodutos.repository;

import com.md.gerenciadorprodutos.model.CategoriaProduto;
import com.md.gerenciadorprodutos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoria(CategoriaProduto categoriaProduto);
}
