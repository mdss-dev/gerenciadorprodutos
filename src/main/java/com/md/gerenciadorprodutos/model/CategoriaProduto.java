package com.md.gerenciadorprodutos.model;

    public enum CategoriaProduto {
        ELETRONICO("eletronico"), ELETRODOMESTICO("eletrodoméstico"),INFORMATICA("informática"),SMARTPHONE("smartphone");
        private String value;
        private CategoriaProduto(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
}