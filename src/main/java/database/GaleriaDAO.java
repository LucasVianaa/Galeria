/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Galeria;
import models.Usuario;

/**
 *
 * @author Aluno
 */
public class GaleriaDAO {
    
    private PreparedStatement select;
    private PreparedStatement selectPorId;
    private PreparedStatement delete;
    private PreparedStatement update;
    private PreparedStatement insert;
    private PreparedStatement selectPorUsuario;

    // Conexao
    private ConexaoPostgreSQL con;

    public GaleriaDAO() throws SQLException {
        try {
            this.con = new ConexaoPostgreSQL(ConexaoPostgreSQL.HOST, ConexaoPostgreSQL.USER, ConexaoPostgreSQL.PASSWORD, ConexaoPostgreSQL.DATABASE);
        } catch (Exception ex) {
            System.out.println("Erro conexao!!");
        }
        this.initStatement();
    }
    
    private void initStatement() throws SQLException {
        this.selectPorId = con.getConnection().prepareStatement("select * from galeria where id = ?;");  
        this.selectPorUsuario = con.getConnection().prepareStatement("select * from galeria where id_usuario = ?;");
        this.select = con.getConnection().prepareStatement("select * from galeria;");
        this.delete = con.getConnection().prepareStatement("delete from galeria where id = ?");
        this.update = con.getConnection().prepareStatement("update galeria set titulo = ?, descricao = ? where id = ?;");
        this.insert = con.getConnection().prepareStatement("insert into galeria (titulo, descricao, id_usuario) values (?,?,?);");
    }

    public Galeria selectPorId(int id) throws SQLException {
        this.selectPorId.setInt(1, id);
        ResultSet rs = this.selectPorId.executeQuery();
        Galeria galeria = new Galeria();
         UsuarioDAO usuarioDao = new UsuarioDAO();
        if (rs.next()) {
            Usuario usuario = usuarioDao.selectPorId(rs.getInt("id_usuario"));
            galeria = new Galeria(rs.getInt("id"), usuario, rs.getString("titulo"), rs.getString("descricao"));
            
        }
        this.selectPorId.close();
        return galeria;
    }
    
    public Galeria selectPorUsuario(Usuario usuario) throws SQLException {
        this.selectPorUsuario.setInt(1, usuario.getId());
        ResultSet rs = this.selectPorUsuario.executeQuery();
        Galeria galeria = new Galeria();
        if (rs.next()) {
            galeria = new Galeria(rs.getInt("id"), usuario, rs.getString("titulo"), rs.getString("descricao"));
            
        }
        this.selectPorUsuario.close();
        return galeria;
    }


    public ArrayList<Galeria> select() throws SQLException {
        ArrayList<Galeria> todos = new ArrayList();
        ResultSet rs;
        UsuarioDAO usuarioDao = new UsuarioDAO();
        try {
            rs = this.select.executeQuery();
            while (rs.next()) {
                Usuario usuario = usuarioDao.selectPorId(rs.getInt("id_usuario"));
                todos.add(new Galeria(rs.getInt("id"), usuario, rs.getString("titulo"), rs.getString("descricao")));
            }
            this.select.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }

    public boolean insert(Galeria galeria) {
        try {
            this.insert.setString(1, galeria.getTitulo());
            this.insert.setString(2, galeria.getDescricao());
            this.insert.setInt(3, galeria.getUsuario().getId());
            this.insert.execute();
            this.insert.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }

    public void delete(int id) throws SQLException {
        this.delete.setInt(1, id);
        this.delete.execute();
        this.delete.close();
    }

    public void deleteMassa(int id[]) {
        for (int i = 0; i < id.length; i++) {
            try {
                this.delete.setInt(1, id[i]);
                this.delete.execute();
            } catch (SQLException ex) {
                Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.delete.close();
        } catch (SQLException ex) {
            Logger.getLogger(GaleriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean update(Galeria galeria) {
        try {
            this.insert.setString(1, galeria.getTitulo());
            this.insert.setString(2, galeria.getDescricao());
            this.update.setInt(3, galeria.getId());
            this.update.execute();
            this.update.close();
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
