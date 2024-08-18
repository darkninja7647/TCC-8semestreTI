/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.FabricaConexao;

/**
 *
 * @author darkn
 */
public class ClienteDAO {
     public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException {

        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "INSERT INTO cliente(Nome,RG,CPF,dataNascimento,sexo,telefone,nacionalidade,sobrenome,cartaodecredito)VALUES(?,?,?,?,?,?,?,?,?)";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getRG());
        pstmt.setString(3, cliente.getCPF());                      
        java.sql.Date sqlDate = new java.sql.Date(cliente.getDataNascimento().getTime());       
        pstmt.setDate(4, sqlDate);
        pstmt.setString(5, cliente.getSexo());
        pstmt.setString(6, cliente.getTelefone());
        pstmt.setString(7, cliente.getNacionalidade());
        pstmt.setString(8, cliente.getSobrenome());
        pstmt.setString(9, cliente.getcartaodecredito());

        pstmt.execute();
        //4
        conexao.close();
    }

    public void excluir(Cliente cliente) throws ClassNotFoundException, SQLException {

        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "DELETE FROM cliente where id = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, cliente.getId());
        pstmt.execute();
        //4
        conexao.close();
    }

    public void alterar(Cliente cliente) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "UPDATE cliente SET nome = ?, RG = ?, CPF = ?, dataNascimento = ?, sexo = ?, telefone = ?, nacionalidade = ?,sobrenome=?, cartaodecredito=?  WHERE id = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, cliente.getNome());
        pstmt.setString(2, cliente.getRG());
        pstmt.setString(3, cliente.getCPF());
        java.sql.Date sqlDate = new java.sql.Date(cliente.getDataNascimento().getTime());
        pstmt.setDate(4, sqlDate);
        pstmt.setString(5, cliente.getSexo());
        pstmt.setString(6, cliente.getTelefone());
        pstmt.setString(7, cliente.getNacionalidade());
        pstmt.setString(8, cliente.getSobrenome());
        pstmt.setString(9, cliente.getcartaodecredito());
        pstmt.setInt(10, cliente.getId());
        pstmt.execute();
        //4
        conexao.close();

    }

    public List<Cliente> consultar() throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "select * from cliente";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Cliente> lista = new ArrayList<Cliente>();
        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setRG(rs.getString("RG"));
            cli.setCPF(rs.getString("CPF"));
            cli.setDataNascimento(rs.getDate("dataNascimento"));
            cli.setSexo(rs.getString("sexo"));
            cli.setNacionalidade(rs.getString("nacionalidade"));
            cli.setSobrenome(rs.getString("sobrenome"));
            cli.setcartaodecredito(rs.getString("cartaodecredito"));
            
            lista.add(cli);
        }
        //4
        return lista;
    }

    //consultar por id
    public Cliente consultarById(Cliente cliente) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "select * from cliente where id = ?";
        //3       
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, cliente.getId());
        ResultSet rs = pstmt.executeQuery();

        Cliente cli = new Cliente();
        if (rs.next()) {
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setRG(rs.getString("RG"));
            cli.setCPF(rs.getString("CPF"));
            cli.setDataNascimento(rs.getDate("dataNascimento"));
            cli.setSexo(rs.getString("sexo"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setNacionalidade(rs.getString("nacionalidade"));
            cli.setSobrenome(rs.getString("sobrenome"));
            cli.setcartaodecredito(rs.getString("cartaodecredito"));
        }

        //4
        return cli;
    }
}
