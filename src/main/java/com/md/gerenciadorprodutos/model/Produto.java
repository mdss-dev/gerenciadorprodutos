package com.md.gerenciadorprodutos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String descricao;
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    public void setCategoria(CategoriaProduto categoria){
        this.categoria = categoria;
    }
}
