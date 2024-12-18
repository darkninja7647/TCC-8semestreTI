package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Request {

    private int idRequest;
    private int id_Room;
    private int id_CollaboratorRequest;
    private int id_CollaboratorResponse;
    private int id_TypeRequest;
    private Date startDate;
    private String descriptionRequest;
    private String requestStatus;
    private Date endDate;

    // Objetos relacionados
    private Room room;
    private Employee collaboratorRequest;
    private Employee collaboratorResponse;
    private TypeRequest typeRequest;

    // Getters e Setters para os IDs e atributos
    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public int getId_Room() {
        return id_Room;
    }

    public void setId_Room(int id_Room) {
        this.id_Room = id_Room;
    }

    public int getId_CollaboratorRequest() {
        return id_CollaboratorRequest;
    }

    public void setId_CollaboratorRequest(int id_CollaboratorRequest) {
        this.id_CollaboratorRequest = id_CollaboratorRequest;
    }

    public int getId_CollaboratorResponse() {
        return id_CollaboratorResponse;
    }

    public void setId_CollaboratorResponse(int id_CollaboratorResponse) {
        this.id_CollaboratorResponse = id_CollaboratorResponse;
    }

    public int getId_TypeRequest() {
        return id_TypeRequest;
    }

    public void setId_TypeRequest(int id_TypeRequest) {
        this.id_TypeRequest = id_TypeRequest;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getDescriptionRequest() {
        return descriptionRequest;
    }

    public void setDescriptionRequest(String descriptionRequest) {
        this.descriptionRequest = descriptionRequest;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Getters e Setters para os objetos relacionados
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Employee getCollaboratorRequest() {
        return collaboratorRequest;
    }

    public void setCollaboratorRequest(Employee collaboratorRequest) {
        this.collaboratorRequest = collaboratorRequest;
    }

    public Employee getCollaboratorResponse() {
        return collaboratorResponse;
    }

    public void setCollaboratorResponse(Employee collaboratorResponse) {
        this.collaboratorResponse = collaboratorResponse;
    }

    public TypeRequest getTypeRequest() {
        return typeRequest;
    }

    public void setTypeRequest(TypeRequest typeRequest) {
        this.typeRequest = typeRequest;
    }

    // Builder mantido conforme fornecido
    public static RequestBuilder getBuilder() {
        return new RequestBuilder();
    }

    public static class RequestBuilder {

        private Request request = new Request();

        public RequestBuilder withIdRequest(int idRequest) {
            request.idRequest = idRequest;
            return this;
        }

        public RequestBuilder comIdRoom(int idRoom) {
            request.id_Room = idRoom;
            return this;
        }

        public RequestBuilder withIdCollaboratorRequest(int idCollaboratorRequest) {
            request.id_CollaboratorRequest = idCollaboratorRequest;
            return this;
        }

        public RequestBuilder withIdCollaboratorResponse(int idCollaboratorResponse) {
            request.id_CollaboratorResponse = idCollaboratorResponse;
            return this;
        }

        public RequestBuilder withIdTypeRequest(int idTypeRequest) {
            request.id_TypeRequest = idTypeRequest;
            return this;
        }

        public RequestBuilder withStartDate(int day, int month, int year) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            String data = year + "-" + month + "-" + day;
            try {
                request.startDate = sdf.parse(data);
            } catch (Exception ex) {
                // Handle exception
            }
            return this;
        }

        public RequestBuilder withdDescriptionRequest(String descriptionRequest) {
            request.descriptionRequest = descriptionRequest;
            return this;
        }

        public RequestBuilder withRequestStatus(String requestStatus) {
            request.requestStatus = requestStatus;
            return this;
        }

        public RequestBuilder withEndDate(int day, int month, int year) {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
            String data = year + "-" + month + "-" + day;
            try {
                request.endDate = sdf.parse(data);
            } catch (Exception ex) {
                // Handle exception
            }
            return this;
        }

        public Request build() {
            return request;
        }
    }
    
 public String formatStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.startDate);
    }
    public static String validateRequestFields(String description) {
        StringBuilder validationMessage = new StringBuilder();

        // Validação dos campos usando um mapa de campos
        Map<String, String> fieldValidations = new HashMap<>();
        fieldValidations.put("Setor", description);

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
