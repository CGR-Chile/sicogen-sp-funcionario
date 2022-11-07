package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.model.InformeArchivoDTO;
import cl.contraloria.sicogen.model.Informes;
import cl.contraloria.sicogen.model.InformesEstadosAnualBO;
import cl.contraloria.sicogen.utils.ConexionBD;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EstadoInformesDAO {

    /* JdbcTemplate */
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EstadoInformesDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public InformesEstadosAnualBO getEstadoInformeAnual(String codPartida, String codCapitulo, Integer ejercicioId, Integer tipoInforme) throws SQLException {

        Connection cnn = null;
        CallableStatement cs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        ConexionBD con = new ConexionBD();
        InformesEstadosAnualBO inAnualBO= new InformesEstadosAnualBO();
        try {
            cnn = con.abrirConexionAtencion();
            cs   = cnn.prepareCall("{call PKG_REPORTES.RECUPERA_ESTADO_INF_ANUAL_MF(?,?,?,?,?,?) }");
            cs.setString(1, codPartida);//01
            cs.setString(2, codCapitulo);//01
            cs.setInt(3, ejercicioId); //320
            cs.setInt(4, tipoInforme); //1
            //algun dia para la correcciones agregar si es correccion o no
            cs.registerOutParameter(5, OracleTypes.CURSOR);
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            //cs.registerOutParameter(6, OracleTypes.CURSOR); //para los Informes Pendientes

            cs.executeQuery();
            rs1 = (ResultSet) cs.getObject(5);
            int fila = 0;
            while(rs1.next()){
                Informes informe = new Informes();

                informe.setInformeId(rs1.getInt("INFORME_ID"));
                informe.setInformeCodigo(rs1.getString("INFORME_CODIGO"));
                informe.setInformeNombre(rs1.getString("INFORME_NOMBRE"));
                informe.setRowClass("row"+fila);

                if(fila == 0){
                    fila=1;
                }
                else{
                    fila=0;
                }

                inAnualBO.getInfomes().add(informe);
            }
            rs2 = (ResultSet) cs.getObject(6);
            while(rs2.next()){
                InformeArchivoDTO archivo = new InformeArchivoDTO();

                archivo.setArchivoId(rs2.getInt("ARCHIVO_ID"));
                archivo.setPeriodoInformeId(rs2.getInt("PERIODO_INFORME_ID"));
                archivo.setEjercicioId(rs2.getInt("EJERCICIO_ID"));
                archivo.setCertificadoId(rs2.getInt("CERTIFICADO"));
                archivo.setInformeId(rs2.getInt("INFORME_ID"));
                archivo.setPeriodoEjercicioId(rs2.getInt("PERIODO_EJERCICIO_ID"));
                archivo.setPeriodoCodigo(rs2.getString("PERIODO_CODIGO"));
                archivo.setArchivoEstadoId(rs2.getInt("ARCHIVO_ESTADO_ID"));
                archivo.setArchivoEstadoNombre(rs2.getString("ARCHIVO_ESTADO_NOMBRE"));
                archivo.setArchivoUsuario(rs2.getString("USUARIO"));
                archivo.setArchivoFecha(rs2.getString("FECHA"));

                inAnualBO.getEstados().add(archivo);
            }
        } catch (NamingException e) {
            e.fillInStackTrace();

        } catch (SQLException e) {
            e.fillInStackTrace();

        }catch(Exception e){
            e.fillInStackTrace();
        }finally {
            cs.close();
            rs1.close();
            rs2.close();
            cnn.close();
        }
        return inAnualBO;

    }


}
