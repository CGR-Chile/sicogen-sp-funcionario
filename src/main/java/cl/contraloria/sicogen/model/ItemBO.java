package cl.contraloria.sicogen.model;

public class ItemBO {
    private String pkCuenta;
    private String codItem;
    private String nomCuenta;

    public String getPkCuenta() {
        return pkCuenta;
    }

    public void setPkCuenta(String pkCuenta) {
        this.pkCuenta = pkCuenta;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getNomCuenta() {
        return nomCuenta;
    }

    public void setNomCuenta(String nomCuenta) {
        this.nomCuenta = nomCuenta;
    }
}
