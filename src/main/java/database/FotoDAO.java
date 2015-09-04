

package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Foto;
import models.Galeria;


public class FotoDAO {
    
    private PreparedStatement select;
    private PreparedStatement selectPorId;
    private PreparedStatement delete;
    private PreparedStatement insert;
    private PreparedStatement selectPorGaleria;

    // Conexao
    private ConexaoPostgreSQL con;

    public FotoDAO() throws SQLException {
        try {
            this.con = new ConexaoPostgreSQL(ConexaoPostgreSQL.HOST, ConexaoPostgreSQL.USER, ConexaoPostgreSQL.PASSWORD, ConexaoPostgreSQL.DATABASE);
        } catch (Exception ex) {
            System.out.println("Erro conexao!!");
        }
        this.initStatement();
    }
    
    private void initStatement() throws SQLException {
        this.selectPorId = con.getConnection().prepareStatement("select * from foto where id = ?;");  
        this.selectPorGaleria = con.getConnection().prepareStatement("select * from foto where id_galeria = ?;");
        this.select = con.getConnection().prepareStatement("select * from foto;");
        this.delete = con.getConnection().prepareStatement("delete from foto where id = ?");
        this.insert = con.getConnection().prepareStatement("insert into foto (descricao, id_galeria) values (?,?);");
    }

    public Foto selectPorId(int id) throws SQLException {
        this.selectPorId.setInt(1, id);
        ResultSet rs = this.selectPorId.executeQuery();
        Foto foto = new Foto();
        GaleriaDAO usuarioDao = new GaleriaDAO();
        if (rs.next()) {
            Galeria galeria = usuarioDao.selectPorId(rs.getInt("id_usuario"));
            foto = new Foto(rs.getInt("id"),  rs.getString("descricao"), galeria);
        }
        this.selectPorId.close();
        return foto;
    }
    
    public List<Foto> selectPorGaleria(Galeria galeria) throws SQLException {
        ArrayList<Foto> todos = new ArrayList();
        ResultSet rs;
        this.selectPorGaleria.setInt(1, galeria.getId());
        try {
            rs = this.selectPorGaleria.executeQuery();
            while (rs.next()) {
                
               todos.add(new Foto(rs.getInt("id"),  rs.getString("descricao"), galeria));
            }
            this.selectPorGaleria.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }


    public ArrayList<Foto> select() throws SQLException {
        ArrayList<Foto> todos = new ArrayList();
        ResultSet rs;
        GaleriaDAO usuarioDao = new GaleriaDAO();
        try {
            rs = this.select.executeQuery();
            while (rs.next()) {
                Galeria galeria = usuarioDao.selectPorId(rs.getInt("id_usuario"));
                todos.add(new Foto(rs.getInt("id"),  rs.getString("descricao"), galeria));
            }
            this.select.close();
        } catch (SQLException e) {
            return todos;
        }
        return todos;
    }

    public boolean insert(Foto foto) {
        try {
            this.insert.setString(1, foto.getDescricao());
            this.insert.setInt(2, foto.getGaleria().getId());
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
                Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.delete.close();
        } catch (SQLException ex) {
            Logger.getLogger(FotoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
