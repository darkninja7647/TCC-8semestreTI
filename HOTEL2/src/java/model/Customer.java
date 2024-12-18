/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author darkn
 */
public class Customer {

    private int idCustomer;
    private String nameCustomer;
    private String identifierDocument;
    private Date birthDate;
    private String gender;
    private String phoneNumber;

    public int getIdCustomer() {
        return this.idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getIdentifierDocument() {
        return identifierDocument;
    }

    public void setIdentifierDocument(String identifierDocument) {
        this.identifierDocument = identifierDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // ----------------------------BUILDER-------------------------------------
    public static CustomerBuilder getBuilder() {
        return new CustomerBuilder();
    }

    public static class CustomerBuilder {

        private String errorMessage;
        private Customer cli = new Customer();

        public CustomerBuilder withCustomerId(int customerId) {
            cli.idCustomer = customerId;
            return this;
        }

        public CustomerBuilder withCustomerName(String customerName) {
            cli.nameCustomer = customerName;
            return this;
        }

        public CustomerBuilder withIdentifierDocument(String identifierDocument) {
            cli.identifierDocument = identifierDocument;
            return this;
        }

        public CustomerBuilder withBirthDate(String birthDate) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(birthDate);
                Date today = new Date();

                if (date.after(today)) {
                    errorMessage = "A data de nascimento não pode ser no futuro.";
                } else {
                    cli.setBirthDate(date);
                }
            } catch (ParseException e) {
                errorMessage = "Erro ao formatar a data de nascimento. Verifique o formato.";
            }
            return this;
        }

        public CustomerBuilder withGender(String gender) {
            cli.gender = gender;
            return this;
        }

        public CustomerBuilder withPhoneNumber(String phoneNumber) {
            cli.phoneNumber = phoneNumber;
            return this;
        }

        public Customer build() {
            return cli;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }

    // ----------------------------Metodos-------------------------------------
    //formatar a data para data, mes e ano
    public String formatBirthDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.birthDate);
    }

    // validacao de erros de algumas variaveis
    public static String validateCustomer(String nameCustomer, String identifierDocument, String birthDate, String gender, String phoneNumber) {
        StringBuilder validationMessage = new StringBuilder();

        // Validação dos campos usando um mapa de campos
        Map<String, String> fieldValidations = new HashMap<>();
        fieldValidations.put("Nome do cliente", nameCustomer);
        fieldValidations.put("Documento", identifierDocument);
        fieldValidations.put("data de nascimento", birthDate);
        fieldValidations.put("Numero de telefone", phoneNumber);

        // Verificando cada campo
        for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
            // Verifica se o campo está vazio ou nulo
            if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
                validationMessage.append("O ").append(entry.getKey()).append(" campo é obrigatório.\\n");
            }
        }
//verificar o valor da data para nao ser nulo
        if (birthDate != null && !birthDate.trim().isEmpty()) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(birthDate);
                Date today = new Date();
                //verificar se a data irá acontecer.
                if (date.after(today)) {
                    validationMessage.append("A data de nascimento não pode ser no futuro.\\n");
                }
            } catch (ParseException e) {
                validationMessage.append("Erro ao formatar a data de nascimento. Verifique o formato. (Formato esperado: yyyy-MM-dd)\\n");
            }
        }

        return validationMessage.toString();
    }

}
