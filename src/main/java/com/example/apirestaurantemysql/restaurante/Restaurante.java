package com.example.apirestaurantemysql.restaurante;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    private String nomeRestaurante;
    private String tipoRestaurante;
    private String homePage;
    private String cidade;
    private String telefone;
    private String endereco;
    
    // Getters e Setters

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNomeRestaurante() {
        return nomeRestaurante;
    }
    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getTipoRestaurante() {
        return tipoRestaurante;
    }
    public void setTipoRestaurante(String tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }

    public String getHomePage() {
        return homePage;
    }
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}

