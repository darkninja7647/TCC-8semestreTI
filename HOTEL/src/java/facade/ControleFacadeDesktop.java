/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facade;
import dao.ClienteDAO;
import java.sql.SQLException;
import model.Cliente;
/**
 *
 * @author LIDIA
 */
public class ControleFacadeDesktop {
    public String acionarCadastrar(Cliente cli){
        
        ClienteDAO clidao = new ClienteDAO();
        String message = "";
        try {
            clidao.cadastrar(cli);
            message = "CADATRADO COM SUCESSO";
        } catch (ClassNotFoundException | SQLException ex) {
            message = "CADASTRO NÃO REALIZADO: " + ex.getMessage();
            System.out.println("Erro: " + ex.getMessage());
        }
        return message;
    }
}
