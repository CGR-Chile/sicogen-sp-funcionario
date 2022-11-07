package cl.contraloria.sicogen.model;

public class SubTipoInforme {
    private int subTipoID;
    private int codigo;
    private int tipoInfoID;
    private String nombre;
    private String isValid;
    private int userCreate;
    private int userUpdate;

    public int getSubTipoID() {
        return subTipoID;
    }

    public void setSubTipoID(int subTipoID) {
        this.subTipoID = subTipoID;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getTipoInfoID() {
        return tipoInfoID;
    }

    public void setTipoInfoID(int tipoInfoID) {
        this.tipoInfoID = tipoInfoID;
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

    public int getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(int userCreate) {
        this.userCreate = userCreate;
    }

    public int getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(int userUpdate) {
        this.userUpdate = userUpdate;
    }
}
