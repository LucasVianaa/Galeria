/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

/**
 *
 * @author Aluno
 */
public class Foto {
    private int id;
    private String descricao;
    private Galeria galeria;

    public Foto() {
    }

    public Foto(int id, String descricao, Galeria galeria) {
        this.id = id;
        this.descricao = descricao;
        this.galeria = galeria;
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }
    
    

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
