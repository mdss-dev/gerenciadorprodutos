package com.md.gerenciadorprodutos.repositorio;

import com.md.gerenciadorprodutos.modelo.CategoriaProduto;
import com.md.gerenciadorprodutos.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {
    List<Produto> findAllByCategoria(CategoriaProduto categoriaProduto);
}
