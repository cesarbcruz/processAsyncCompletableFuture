/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.io.Serializable;

/**
 *
 * @author cesar
 */
public class NotificacaoResultado implements Serializable{
    private String data;
    private boolean sucesso;

    public NotificacaoResultado(String data, boolean sucesso) {
        this.data = data;
        this.sucesso = sucesso;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isSucesso() {
        return sucesso;
    }
    
    public boolean isFalha() {
        return !sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

   
    
    
}
