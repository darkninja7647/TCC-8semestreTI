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
public class TypeRequest {
    
    private int idTypeRequest;
    private String TypeRequest;
    
    public int getIdTypeRequest() {
        return idTypeRequest;
    }

    public void setIdTypeRequest(int idTypeRequest) {
        this.idTypeRequest = idTypeRequest;
    }

    public String getTypeRequest() {
        return TypeRequest;
    }

    public void setTypeRequest(String TypeRequest) {
        this.TypeRequest = TypeRequest;
    }
    // --------------------------------------BUILDER----------------------------
    public static TypeRequestBuilder getBuilder() {
        return new TypeRequestBuilder();
    }

    public static class TypeRequestBuilder {

        private TypeRequest typerequest = new TypeRequest();
        
        public TypeRequestBuilder withIdTypeRequest(int idTypeRequest) {
            typerequest.idTypeRequest = idTypeRequest;
            return this;
        }

        public TypeRequestBuilder withTypeRequest(String typeRequest) {
            typerequest.TypeRequest = typeRequest;
            return this;
        }
        
           public TypeRequest build() {
            return typerequest;
        }

    }
    
    public static String validateTypeRequestFields(String TypeRequest) {
        StringBuilder validationMessage = new StringBuilder();
        Map<String, String> fieldValidations = new HashMap<>();
        fieldValidations.put("TipoSolicitação", TypeRequest);
        for (Map.Entry<String, String> entry : fieldValidations.entrySet()) {
            // Verifica se o campo está vazio ou nulo
            if (entry.getValue() == null || entry.getValue().trim().isEmpty()) {
                validationMessage.append("O ").append(entry.getKey()).append(" campo é obrigatório.\\n");
            }
        }
        return validationMessage.toString();
    }
}
 