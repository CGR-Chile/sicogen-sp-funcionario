/**
 * Error.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cl.cgr.www.OSB.SectorPublico.TDRMP.V1.EXP.ValidacionInforme;

public class Error  implements java.io.Serializable {
    private String codigoCuenta;

    private short estadoRegla;

    private short idRegla;

    private short tipoError;

    private String mensajeError;

    private short identificaSalida;

    private short seccion;

    public Error() {
    }

    public Error(
           String codigoCuenta,
           short estadoRegla,
           short idRegla,
           short tipoError,
           String mensajeError,
           short identificaSalida,
           short seccion) {
           this.codigoCuenta = codigoCuenta;
           this.estadoRegla = estadoRegla;
           this.idRegla = idRegla;
           this.tipoError = tipoError;
           this.mensajeError = mensajeError;
           this.identificaSalida = identificaSalida;
           this.seccion = seccion;
    }


    /**
     * Gets the codigoCuenta value for this Error.
     * 
     * @return codigoCuenta
     */
    public String getCodigoCuenta() {
        return codigoCuenta;
    }


    /**
     * Sets the codigoCuenta value for this Error.
     * 
     * @param codigoCuenta
     */
    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }


    /**
     * Gets the estadoRegla value for this Error.
     * 
     * @return estadoRegla
     */
    public short getEstadoRegla() {
        return estadoRegla;
    }


    /**
     * Sets the estadoRegla value for this Error.
     * 
     * @param estadoRegla
     */
    public void setEstadoRegla(short estadoRegla) {
        this.estadoRegla = estadoRegla;
    }


    /**
     * Gets the idRegla value for this Error.
     * 
     * @return idRegla
     */
    public short getIdRegla() {
        return idRegla;
    }


    /**
     * Sets the idRegla value for this Error.
     * 
     * @param idRegla
     */
    public void setIdRegla(short idRegla) {
        this.idRegla = idRegla;
    }


    /**
     * Gets the tipoError value for this Error.
     * 
     * @return tipoError
     */
    public short getTipoError() {
        return tipoError;
    }


    /**
     * Sets the tipoError value for this Error.
     * 
     * @param tipoError
     */
    public void setTipoError(short tipoError) {
        this.tipoError = tipoError;
    }


    /**
     * Gets the mensajeError value for this Error.
     * 
     * @return mensajeError
     */
    public String getMensajeError() {
        return mensajeError;
    }


    /**
     * Sets the mensajeError value for this Error.
     * 
     * @param mensajeError
     */
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }


    /**
     * Gets the identificaSalida value for this Error.
     * 
     * @return identificaSalida
     */
    public short getIdentificaSalida() {
        return identificaSalida;
    }


    /**
     * Sets the identificaSalida value for this Error.
     * 
     * @param identificaSalida
     */
    public void setIdentificaSalida(short identificaSalida) {
        this.identificaSalida = identificaSalida;
    }


    /**
     * Gets the seccion value for this Error.
     * 
     * @return seccion
     */
    public short getSeccion() {
        return seccion;
    }


    /**
     * Sets the seccion value for this Error.
     * 
     * @param seccion
     */
    public void setSeccion(short seccion) {
        this.seccion = seccion;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Error)) return false;
        Error other = (Error) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoCuenta==null && other.getCodigoCuenta()==null) || 
             (this.codigoCuenta!=null &&
              this.codigoCuenta.equals(other.getCodigoCuenta()))) &&
            this.estadoRegla == other.getEstadoRegla() &&
            this.idRegla == other.getIdRegla() &&
            this.tipoError == other.getTipoError() &&
            ((this.mensajeError==null && other.getMensajeError()==null) || 
             (this.mensajeError!=null &&
              this.mensajeError.equals(other.getMensajeError()))) &&
            this.identificaSalida == other.getIdentificaSalida() &&
            this.seccion == other.getSeccion();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodigoCuenta() != null) {
            _hashCode += getCodigoCuenta().hashCode();
        }
        _hashCode += getEstadoRegla();
        _hashCode += getIdRegla();
        _hashCode += getTipoError();
        if (getMensajeError() != null) {
            _hashCode += getMensajeError().hashCode();
        }
        _hashCode += getIdentificaSalida();
        _hashCode += getSeccion();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Error.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.cgr.cl/OSB/SectorPublico/TDRMP/V1/EXP/ValidacionInforme", "error"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCuenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoCuenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoRegla");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoRegla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idRegla");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idRegla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensajeError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificaSalida");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificaSalida"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
