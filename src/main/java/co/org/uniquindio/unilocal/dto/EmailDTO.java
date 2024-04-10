package co.org.uniquindio.unilocal.dto;

public class EmailDTO {
    /*
     * atributos de la clase EmailDTO
     */
    String asunto;
    String cuerpo;
    String destinatario;


    /*
     * Constructor de la clase EmailDTO
     */
    public EmailDTO(String asunto, String cuerpo, String destinatario) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }

    /*
     * Getters y Setters de la clase EmailDTO
     */
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }


}
