package br.edu.ifsp.scl.sdm.dicesdm.model;

import java.io.Serializable;

public class Configuration implements Serializable {

    private Integer qtdNumeros;

    public Configuration() {
        this.qtdNumeros = 10;
    }

    public Configuration(Integer qtdNumeros) {
        this.qtdNumeros = qtdNumeros;
    }

    public Integer getQtdNumeros() {
        return qtdNumeros;
    }

    public void setQtdNumeros(Integer qtdNumeros) {
        this.qtdNumeros = qtdNumeros;
    }
}
