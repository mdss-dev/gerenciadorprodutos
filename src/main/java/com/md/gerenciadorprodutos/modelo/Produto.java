package com.md.gerenciadorprodutos.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
