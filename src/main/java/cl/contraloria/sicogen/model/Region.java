package cl.contraloria.sicogen.model;

import java.sql.Date;

public class Region {
    private int regId;
    private String codigo;
    private String nombre;
    private String isValid;
    private String userCreate;
    private Date dateCreate;
    private String userUpdate;
    private Date dateUpdate;
    public Date getDateCreate() {
        return dateCreate;
    }
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    public Date getDateUpdate() {
        return dateUpdate;
    }
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
    public void setRegId(int regId) {
        this.regId = regId;
    }

    public int getRegId() {
        return regId;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getIsValid() {
        return isValid;
    }
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    public String getUserCreate() {
        return userCreate;
    }
    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }
    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }
}