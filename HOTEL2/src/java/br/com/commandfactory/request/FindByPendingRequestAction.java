/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.commandfactory.request;

import dao.RequestDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Request;

/**
 *
 * @author LIDIA
 */
public class FindByPendingRequestAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        RequestDAO requestdao = new RequestDAO();

        try {
            // Obtém o usuário logado da sessão
            Account account = (Account) request.getSession().getAttribute("account");

            // Busca todas as solicitações pendentes
            List<Request> allPendingRequests = requestdao.findByPendingRequest();
            List<Request> filteredRequests = new ArrayList<>();

            // Imprime o ID do usuário logado para depuração
            System.out.println("ID do usuário logado: " + account.getId_Employee());
            System.out.println("Nível de acesso do usuário: " + account.getAccessLevel());

            // Filtra as solicitações com base no nível de acesso
            if ("administrador".equalsIgnoreCase(account.getAccessLevel())
                    || "recepcao".equalsIgnoreCase(account.getAccessLevel())) {
                // Administradores e recepcionistas veem todas as solicitações pendentes
                filteredRequests = allPendingRequests;
            } else {
                // Outros usuários veem apenas as solicitações destinadas a eles
                for (Request req : allPendingRequests) {
                    // Depuração para verificar os valores
                    System.out.println("ID do funcionário na solicitação: " + req.getCollaboratorResponse().getIdEmployee());
                    System.out.println("Comparando com o ID do usuário logado: " + account.getId_Employee());

                    // Se o nível de acesso não for 'administrador' ou 'recepcao', filtra pelas solicitações
                    // destinadas ao colaborador logado
                    if (req.getCollaboratorResponse().getIdEmployee() == account.getId_Employee()) {
                        filteredRequests.add(req);
                    }
                }
            }

            // Define a lista filtrada como atributo para o JSP
            request.setAttribute("listarequest", filteredRequests);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

        return "request.jsp";
    }
}
