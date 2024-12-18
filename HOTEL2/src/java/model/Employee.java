/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LIDIA
 */
public class Employee {

    private int idEmployee;
    private String nameEmployee;
    private String identifierDocumentEmployee;
    private String sector;
    private String statusEmployee;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getIdentifierDocumentEmployee() {
        return identifierDocumentEmployee;
    }

    public void setIdentifierDocumentEmployee(String identifierDocumentEmployee) {
        this.identifierDocumentEmployee = identifierDocumentEmployee;
    }

    public String getStatusEmployee() {
        return statusEmployee;
    }

    public void setStatusEmployee(String statusEmployee) {
        this.statusEmployee = statusEmployee;
    }

    // ----------------------------BUILDER-------------------------------------
    public static EmployeeBuilder getBuilder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {

        private Employee emp = new Employee();

        public EmployeeBuilder withIdEmployee(int id) {
            emp.idEmployee = id;
            return this;
        }

        public EmployeeBuilder withNameEmployee(String nameEmployee) {
            emp.nameEmployee = nameEmployee;
            return this;
        }

        public EmployeeBuilder withIdentifierDocumentEmployee(String cpf) {
            emp.identifierDocumentEmployee = cpf;
            return this;
        }

        public EmployeeBuilder withSector(String sector) {
            emp.sector = sector;
           return this;
        }

        public EmployeeBuilder withstatusEmployee(String statusEmployee) {
            emp.statusEmployee = statusEmployee;
            return this;
        }

        public Employee build() {
            return emp;
        }
    }
    
        // ----------------------------Metodos-------------------------------------
    // validacao de erros de algumas variaveis

    public static String validateEmployeeFields(String nameEmployee, String identifierDocumentEmployee, String sector) {
    StringBuilder validationMessage = new StringBuilder();

    // Validação dos campos usando um mapa de campos
    Map<String, String> fieldValidations = new HashMap<>();
    fieldValidations.put("Nome do Funcionario", nameEmployee);
    fieldValidations.put("Documento", identifierDocumentEmployee);
    fieldValidations.put("Setor", sector);


    // Verificando cada campo
    for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
        // Verifica se o campo está vazio ou nulo
        if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
            validationMessage.append("O ").append(entry.getKey()).append(" campo é obrigatório.\\n");
        }
    }
    return validationMessage.toString();
}
    
}
