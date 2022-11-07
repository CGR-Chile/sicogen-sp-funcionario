package cl.contraloria.sicogen.utils;

import cl.contraloria.sicogen.model.Informes;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.SQLException;

public class Utils {

    public static String readClob(Clob clob) throws SQLException, IOException {

       // logger.info("clob: "+clob);

        StringBuilder sb = new StringBuilder((int) clob.length());
        Reader r = clob.getCharacterStream();
        char[] cbuf = new char[2048];
        int n;
        while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
            sb.append(cbuf, 0, n);
        }
        return sb.toString();
    }

    public Informes getImagenEstado(int estado, int infId){

        Informes inf = new Informes();
        switch (estado){
            case 1:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/loader.gif");
                inf.setImgRV("/sicogen/resources/img/blanco.png");
                inf.setInformeMensaje("");
                break;
            case 2:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/loader.gif");
                inf.setImgRV("/sicogen/resources/img/blanco.png");
                inf.setInformeMensaje("");
                break;
            case 3:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/error.png");
                inf.setImgRV("/sicogen/resources/img/document_inspector.png");
                inf.setInformeAccion("verReporteValidacion("+infId+")");
                inf.setInformeMensaje("");
                break;
            case 4:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/Validado.png");
                inf.setImgRV("/sicogen/resources/img/document_inspector.png");
                inf.setInformeAccion("verReporteValidacion("+infId+")");
                inf.setInformeMensaje("");
                break;
            case 5:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/ValidadoOBS.png");
                inf.setImgRV("/sicogen/resources/img/document_inspector.png");
                inf.setInformeAccion("verReporteValidacion("+infId+")");
                inf.setInformeMensaje("");
                break;
            case 6:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/Procesado.png");
                inf.setImgRV("/sicogen/resources/img/document_inspector.png");
                inf.setInformeAccion("verReporteValidacion("+infId+")");
                inf.setInformeMensaje("Proceso");
                break;
            case 7:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/ProcesadoOBS.png");
                inf.setImgRV("/sicogen/resources/img/document_inspector.png");
                inf.setInformeAccion("verReporteValidacion("+infId+")");
                inf.setInformeMensaje("Proceso con Observaciones");
                break;
            case 8:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/NotMov.png");
                inf.setImgRV("/sicogen/resources/img/blanco.png");
                inf.setInformeMensaje("Sin Movimiento");
                break;
            case 9:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/NotMovProc.png");
                inf.setImgRV("/sicogen/resources/img/blanco.png");
                inf.setInformeMensaje("Anulado");
                break;
            case 10:
                inf.setImgCarga("/sicogen/resources/img/blanco.png");
                inf.setImgValid("/sicogen/resources/img/NotMovProc.png");
                inf.setImgRV("/sicogen/resources/img/blanco.png");
                inf.setInformeMensaje("Procesado sin movimiento");
                break;
        }
        return inf;
    }

    public static Document convertStringToXMLDocument(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        xmlString = removeXmlStringNamespaceAndPreamble(xmlString);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
        return doc;
    }

    private static String removeXmlStringNamespaceAndPreamble(String xmlString) {
        return xmlString.replaceAll("(<\\?[^<]*\\?>)?", ""). /* remove preamble */
                replaceAll("xmlns.*?(\"|\').*?(\"|\')", "") /* remove xmlns declaration */
                .replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */
                .replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */
    }
}
