/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Usuario;
import util.FabricaConexao;

/**
 *
 * @author darkn
 */
public class UsuarioDAO {
    public boolean Login(Usuario u) throws ClassNotFoundException, SQLException {
        
        boolean aux = false;
        
        Connection conexao = FabricaConexao.getConexao();
        
        String sql = "select id, email, senha "
                + "from usuario where email = ? and senha = ?";
        
        PreparedStatement comando = conexao.prepareStatement(sql);
        
        comando.setString(1, u.getEmail());
        comando.setString(2, u.getSenha());
        
        ResultSet resultado = comando.executeQuery();
        
        if (resultado.next()) {
            u.setId(resultado.getInt("id"));
            u.setEmail(resultado.getString("email"));
            u.setSenha(resultado.getString("senha"));
            aux = true;
        }
        
        conexao.close();
        return aux;
       
    }
    public void Cadastrar(Usuario u) throws ClassNotFoundException, SQLException {
        
        Connection conexao = FabricaConexao.getConexao();
        
        String sql = "insert into usuario (email, senha) values (?, ?)";
        
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, u.getEmail());
        comando.setString(2, u.getSenha());
        comando.execute();
        conexao.close();
    }
}
