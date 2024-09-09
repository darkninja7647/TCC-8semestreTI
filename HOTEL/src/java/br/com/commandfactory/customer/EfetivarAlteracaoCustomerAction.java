/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.customer;

import dao.CustomerDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import model.Customer;

/**
 *
 * @author LIDIA
 */
public class EfetivarAlteracaoCustomerAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("id: " + request.getParameter("txtid"));
        int id = Integer.parseInt(request.getParameter("txtid"));
        String nome = request.getParameter("txtnome");
        String RG = request.getParameter("txtRG");
        String CPF = request.getParameter("txtCPF");
        LocalDate dataNascimento = LocalDate.parse(request.getParameter("txtdataNascimento"));       
        int ano = dataNascimento.getYear();
        int mes = dataNascimento.getMonthValue();
        int dia = dataNascimento.getDayOfMonth();     
        String sexo = request.getParameter("txtsexo");
        String telefone = request.getParameter("txttelefone");

        CustomerDAO clidao = new CustomerDAO();
        Customer cli = Customer.getBuilder()
                .comId(id)
                .comNome(nome)
                .comRG(RG)
                .comCPF(CPF)
                .comDataNascimento(dia, mes, ano)
                .comSexo(sexo)
                .comTelefone(telefone)
                .constroi();

       
        try {
            clidao.alterar(cli);
            List<Customer> lista = clidao.consultar();
            request.setAttribute("lista", lista);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return "inicio.jsp";
    }

}
