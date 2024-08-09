/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.ClienteDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Cliente;

/**
 *
 * @author LIDIA
 */
public class CadastrarClienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
    
        ClienteDAO clidao = new ClienteDAO();
        Cliente cli = Cliente.getBuilder()
                .comNome(request.getParameter("txtnome"))
                .comRG(request.getParameter("txtRG"))
                .comCPF(request.getParameter("txtCPF"))
                .comDataNascimento(Integer.parseInt(request.getParameter("txtdia")), Integer.parseInt(request.getParameter("txtmes")), Integer.parseInt(request.getParameter("txtano")))
                .comSexo(request.getParameter("txtsexo"))
                .comTelefone(request.getParameter("txttelefone"))
                .comSobrenome(request.getParameter("txtsobrenome"))
                .comNacionalidade(request.getParameter("txtnacionalidade"))
                .comCartaodecredito(request.getParameter("txtcartaodecredito"))
                .constroi();
        try {
            clidao.cadastrar(cli);
            List<Cliente> lista = clidao.consultar();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "inicio.jsp";
    }
}
