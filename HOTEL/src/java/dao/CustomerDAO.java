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
import model.Customer;
import util.FabricaConexao;

/**
 *
 * @author darkn
 */
public class CustomerDAO {
     public void cadastrar(Customer customer) throws ClassNotFoundException, SQLException {

        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "INSERT INTO customer(Nome,RG,CPF,dataNascimento,sexo,telefone)VALUES(?,?,?,?,?,?)";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, customer.getNome());
        pstmt.setString(2, customer.getRG());
        pstmt.setString(3, customer.getCPF());                      
        java.sql.Date sqlDate = new java.sql.Date(customer.getDataNascimento().getTime());       
        pstmt.setDate(4, sqlDate);
        pstmt.setString(5, customer.getSexo());
        pstmt.setString(6, customer.getTelefone());

        pstmt.execute();
        //4
        conexao.close();
    }

    public void excluir(Customer customer) throws ClassNotFoundException, SQLException {

        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "DELETE FROM customer where id = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, customer.getId());
        pstmt.execute();
        //4
        conexao.close();
    }

    public void alterar(Customer customer) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "UPDATE customer SET nome = ?, RG = ?, CPF = ?, dataNascimento = ?, sexo = ?, telefone = ?  WHERE id = ?";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setString(1, customer.getNome());
        pstmt.setString(2, customer.getRG());
        pstmt.setString(3, customer.getCPF());
        java.sql.Date sqlDate = new java.sql.Date(customer.getDataNascimento().getTime());
        pstmt.setDate(4, sqlDate);
        pstmt.setString(5, customer.getSexo());
        pstmt.setString(6, customer.getTelefone());
        pstmt.setInt(7, customer.getId());
        pstmt.execute();
        //4
        conexao.close();

    }

    public List<Customer> consultar() throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "select * from customer";
        //3
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        List<Customer> lista = new ArrayList<Customer>();
        while (rs.next()) {
            Customer cli = new Customer();
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setRG(rs.getString("RG"));
            cli.setCPF(rs.getString("CPF"));
            cli.setDataNascimento(rs.getDate("dataNascimento"));
            cli.setSexo(rs.getString("sexo"));
            
            lista.add(cli);
        }
        //4
        return lista;
    }

    //consultar por id
    public Customer consultarById(Customer customer) throws ClassNotFoundException, SQLException {
        //1
        Connection conexao = FabricaConexao.getConexao();
        //2
        String sql = "select * from customer where id = ?";
        //3       
        PreparedStatement pstmt = conexao.prepareStatement(sql);
        pstmt.setInt(1, customer.getId());
        ResultSet rs = pstmt.executeQuery();

        Customer cli = new Customer();
        if (rs.next()) {
            cli.setId(rs.getInt("id"));
            cli.setNome(rs.getString("nome"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setRG(rs.getString("RG"));
            cli.setCPF(rs.getString("CPF"));
            cli.setDataNascimento(rs.getDate("dataNascimento"));
            cli.setSexo(rs.getString("sexo"));
            cli.setTelefone(rs.getString("telefone"));
        }

        //4
        return cli;
    }
}
