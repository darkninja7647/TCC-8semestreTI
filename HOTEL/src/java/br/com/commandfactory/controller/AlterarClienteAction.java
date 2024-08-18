/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.controller;

import dao.ClienteDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

/**
 *
 * @author LIDIA
 */
public class AlterarClienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        Cliente cli = Cliente.getBuilder()
        .comId(Integer.parseInt(request.getParameter("txtid")))
        .constroi();
        ClienteDAO clidao = new ClienteDAO();
        try {
            cli = clidao.consultarById(cli);
            request.setAttribute("cliente", cli);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "resultadoalterado.jsp";
    }
}
