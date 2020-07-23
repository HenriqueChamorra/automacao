package com.sicredi.model;

import java.util.ArrayList;

public class ApiSimulacaoModel {

    private int  id;

    ArrayList<String> meses = new ArrayList<String>();
    ArrayList<String> valor = new ArrayList<String>();

    public ArrayList<String> getMeses() {
        return meses;
    }

    public ArrayList<String> getValor() {
        return valor;
    }

    public float getId() {
        return id;
    }

}
