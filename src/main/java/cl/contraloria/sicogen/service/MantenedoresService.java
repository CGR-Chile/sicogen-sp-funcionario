package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.EntidadDAO;
import cl.contraloria.sicogen.dao.MantenedorDAO;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;
import cl.contraloria.sicogen.model.ErroresSaldoInicial;
import cl.contraloria.sicogen.model.Mantenedores;
import cl.contraloria.sicogen.model.OptionsJtable;
import cl.contraloria.sicogen.model.SubTipoInforme;
import cl.contraloria.sicogen.model.LogCarga;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenedoresService {

    private MantenedorDAO mantenedorDAO;
    private EntidadDAO entidadDAO;

    public MantenedoresService(MantenedorDAO mantenedorDAO, EntidadDAO entidadDAO) {
        this.mantenedorDAO = mantenedorDAO;
        this.entidadDAO = entidadDAO;
    }

    public List<JsonJTableExpenseBean> getEjercicioMant(String order) {
        return mantenedorDAO.getEjercicioMant(order);
    }
    
    public List<JsonJTableExpenseBean> getPeriodoMant(String order) {
        return mantenedorDAO.getPeriodoMant(order);
    }
    
    public JsonJTableExpenseBean setPeriodoMant(String nombre, String codigo, String usuario) {
    	return mantenedorDAO.setPeriodoMant(nombre,codigo,usuario);
    }
    
    public JsonJTableExpenseBean updPeriodoMant(String id,String nombre, String codigo, String usuario) {
    	return mantenedorDAO.updPeriodoMant(id,nombre,codigo,usuario);
    }
    
    public JsonJTableExpenseBean delPeriodoMant(String codigo, String usuario) {
    	return mantenedorDAO.delPeriodoMant(codigo,usuario);
    }

    public JsonJTableExpenseBean delEjercicioMant(String codigo, String usuario) {
        return mantenedorDAO.delEjercicioMant(codigo, usuario);
    }

    public JsonJTableExpenseBean updEjercicioMant(String areaId, String nombre, String codigo, String usuario) {
        return mantenedorDAO.updEjercicioMant(areaId, nombre, codigo, usuario);
    }

    public JsonJTableExpenseBean addEjercicioMant(String codigo, String nombre, int tipo, String comuna, String usuario) {
        return mantenedorDAO.addEjercicioMant(codigo, nombre, tipo, comuna, usuario);
    }

    public List<JsonJTableExpenseBean> getMonedaMant(String order) {
        return mantenedorDAO.getMonedaMant(order);
    }

    public JsonJTableExpenseBean setMonedaMant(String codigo, String nombre, String usuario) {
        return mantenedorDAO.setMonedaMant(codigo,nombre,usuario);
    }

    public JsonJTableExpenseBean updMonedaMant(String id,String codigo,String nombre,String usuario) {
        return mantenedorDAO.updMonedaMant(id,codigo,nombre,usuario);
    }

    public JsonJTableExpenseBean delMonedaMant(String codigo, String usuario) {
        return mantenedorDAO.delMonedaMant(codigo,usuario);
    }

    public List<JsonJTableExpenseBean> getRolesMant(String order) {
        return mantenedorDAO.getRolesMant(order);
    }

    public JsonJTableExpenseBean setRolesMant(String codigo, String nombre, String usuario,String fecha) {
        return mantenedorDAO.setRolesMant(codigo,nombre,usuario,fecha);
    }

    public JsonJTableExpenseBean updRolesMant(String id,String codigo,String nombre,String usuario) {
        return mantenedorDAO.updRolesMant(id,codigo,nombre,usuario);
    }

    public JsonJTableExpenseBean delRolesMant(String codigo, String usuario) {
        return mantenedorDAO.delRolesMant(codigo,usuario);
    }

    public List<JsonJTableExpenseBean> getProyectosMant(String order) {
        return mantenedorDAO.getProyectosMant(order);
    }

    public JsonJTableExpenseBean setProyectosMant(String codigo, String nombre, String usuario,Integer estado) {
        return mantenedorDAO.setProyectosMant(codigo,nombre,usuario,estado);
    }

    public JsonJTableExpenseBean updProyectosMant(Integer id,String codigo,String nombre,Integer estado,String usuario) {
        return mantenedorDAO.updProyectosMant(id,codigo,nombre,estado,usuario);
    }

    public JsonJTableExpenseBean delProyectosMant(String codigo, String usuario) {
        return mantenedorDAO.delProyectosMant(codigo, usuario);
    }

    public List<JsonJTableExpenseBean> getEtapasCompromisoMant(String order) {
        return mantenedorDAO.getEtapasCompromisoMant(order);}

    public JsonJTableExpenseBean delEtapasCompromisoMant(String areaID, String usuario) {
        return mantenedorDAO.delEtapasCompromisoMant(areaID, usuario);
    }

    public List<JsonJTableExpenseBean> getRegionMant(String order) {
        return mantenedorDAO.getRegionMant(order);

    }

    public JsonJTableExpenseBean updEtapasCompromisoMant(String areaId, String etapa, String nombre, String rut, String usuario) {
        return mantenedorDAO.updEtapasCompromisoMant(areaId, etapa, nombre, rut, usuario);
    }

    public JsonJTableExpenseBean addEtapasCompromisoMant(String etapa, String nombre, String rut, String usuario) {
        return mantenedorDAO.addEtapasCompromisoMant(etapa, nombre, rut, usuario);
    }

    public JsonJTableExpenseBean addProvinciasMant(int regionId, String provCod, String provNom, String usuario) {
        return mantenedorDAO.addProvinciasMant(regionId, provCod, provNom, usuario);
    }

    public JsonJTableExpenseBean updProvinciasMant(int provId, String nombre, String codigo, String usuario) {
        return mantenedorDAO.updProvinciasMant(provId, nombre, codigo, usuario);
    }

    public JsonJTableExpenseBean delProvinciasMant(int idProvincia, String usuario) {
        return mantenedorDAO.delProvinciasMant(idProvincia, usuario);
    }

    public List<JsonJTableExpenseBean> getProvinciasMant(int region, String order) {
        return mantenedorDAO.getProvinciasMant(region, order);
    }

    public List<JsonJTableExpenseBean> getProvinciasRegMant(int region) {
        return mantenedorDAO.getProvinciasRegMant(region);
    }

    public List<SubTipoInforme> loadMantenedorTblInforme(int tipo) {
        return mantenedorDAO.loadMantenedorTblInforme(tipo);
    }

    public List<JsonJTableExpenseBean> getTblInformes(int subTipoInfo) {
        return mantenedorDAO.getTblInformes(subTipoInfo);
    }

    public List<OptionsJtable> getSubTipos() {
        return mantenedorDAO.getSubTipos();
    }

    public JsonJTableExpenseBean addTblInforme(int subTipoInfo, String codigo, String nombre, String descripcion, String usuario) {
        return mantenedorDAO.addTblInforme(subTipoInfo, codigo, nombre, descripcion, usuario);
    }

    public JsonJTableExpenseBean updateTblInforme(JsonJTableExpenseBean Informe) {
        return mantenedorDAO.updateTblInforme(Informe);
    }

    public JsonJTableExpenseBean delTblInforme(String informeID, int usuario) {
        return mantenedorDAO.delTblInforme(informeID, usuario);
    }

    public List<Mantenedores> getMantededores(String user) {
        return entidadDAO.getMantenedores(user);
    }

    public JsonJTableExpenseBean delRegionMant(String codigo, String usuario) {
        return mantenedorDAO.delRegionMant(codigo, usuario);
    }

    public JsonJTableExpenseBean updRegionMant(String areaId, String nombre, String codigo, String usuario) {
        return mantenedorDAO.updRegionMant(areaId, nombre, codigo, usuario);
    }

    public JsonJTableExpenseBean addRegionMant(String codigo, String nombre, String usuario) {
        return mantenedorDAO.addRegionMant(codigo, nombre, usuario);
    }

    public JsonJTableExpenseBean setProyectos(String codigo, String nombre,String usuario, String estado) {
        return mantenedorDAO.setProyectos(codigo, nombre, usuario, estado);
    }

    public List<OptionsJtable> getEstados() {
        return mantenedorDAO.getEstados();
    }

    public List<JsonJTableExpenseBean> getPeriodoEjercicioMant(Integer ejercicio,String order) {
        return mantenedorDAO.getPeriodoEjercicioMant(ejercicio,order);
    }

    public JsonJTableExpenseBean setPeriodoEjerciciosMant(Integer ejercicioId, Integer periodoId, String usuario) {
        return mantenedorDAO.setPeriodoEjercicioMant(ejercicioId, periodoId, usuario);
    }

    public List<CuentaParticularPresupDTO> getCuentasParticularesPresup(Integer idEjercicio,
                                                                  Integer idPartida,
                                                                  Integer idCapitulo,
                                                                  Integer idPrograma) {
        return mantenedorDAO.getCuentasParticularesPresup(idEjercicio, idPartida, idCapitulo, idPrograma);
    }

    public void createCtaParticularPresup(CuentaParticularPresupDTO cta, String usuario) {
        mantenedorDAO.createCtaParticularPresup(cta, usuario);
    }

    public CuentaParticularPresupDTO getCtaParticularPresup(Integer idCuenta) {
        return mantenedorDAO.getCtaParticularPresup(idCuenta);
    }

    public void updateCtaParticularPresup(CuentaParticularPresupDTO cta, String usuario) {
        mantenedorDAO.updateCtaParticularPresup(cta, usuario);
    }

    public List<CuentaParticularPresupDTO> getCtasParticularesDepend(Integer idCuenta) {
        return mantenedorDAO.getCtasParticularesDepend(idCuenta);
    }

    public void desactivarCtaParticulares(Integer idCuenta, String usuario) {
        mantenedorDAO.desactivarCtaParticulares(idCuenta, usuario);
    }

    public void activarCtaParticulares(Integer idCuenta, String usuario) {
        mantenedorDAO.activarCtaParticulares(idCuenta, usuario);
    }

    public BitacoraCtaParticularDTO getRegistroBitacora(Integer idCuenta) {
        return mantenedorDAO.getRegistroBitacora(idCuenta);
    }

    public JsonJTableExpenseBean updPeriodosEjercicioMant(Integer id,Integer padreId,Integer padreId2,String usuario) {
        return mantenedorDAO.updPeriodosEjercicioMant(id,padreId,padreId2,usuario);
    }

    public JsonJTableExpenseBean delPeriodosEjercicioMant(Integer id, String usuario) {
        return mantenedorDAO.delPeriodosEjercicioMant(id, usuario);
    }

    public JsonJTableExpenseBean setMigrarEjercicioMant(Integer ejercicioId, Integer ejercicioId2, String usuario) {
        return mantenedorDAO.setMigrarEjercicioMant(ejercicioId, ejercicioId2, usuario);
    }

    public List<JsonJTableExpenseBean> getSaldoInicialMant(String order) {
        return mantenedorDAO.getSaldoInicialMant(order);
    }

    public JsonJTableExpenseBean setSaldoInicialMant(Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        return mantenedorDAO.setSaldoInicialMant(ejercicio,moneda,codPartida,codCapitulo,codPrograma, cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
    }

    public JsonJTableExpenseBean updSaldoInicialMant(Integer id,Integer ejercicio,String moneda,String codPartida,String codCapitulo,String codPrograma,String cuentaAgregacion,String cuentaN1,String cuentaN2,String cuentaN3,String saldoDeudor,String saldoAcreedor,String usuario) {
        return mantenedorDAO.updSaldoInicialMant(id,ejercicio,moneda,codPartida,codCapitulo,codPrograma,cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
    }

    public JsonJTableExpenseBean delSaldoInicialMant(Integer id, String usuario) {
        return mantenedorDAO.delSaldoInicialMant(id, usuario);
    }

    public List<ErroresSaldoInicial> getErroresSaldoInicialMant() {
        return mantenedorDAO.getErroresSaldoInicialMant();
    }

    public void deleteSaldosCero() {
        mantenedorDAO.deleteSaldosCero();
    }

    public void insertarSaldosCero(String anio, String moneda, String codPartida,String codCapitulo,String codPrograma, String ctaAgregacionMasN1, String saldoDeudor,String saldoAcreedor, String usuario) {
        mantenedorDAO.insertarSaldosCero(anio,moneda,codPartida,codCapitulo,codPrograma,ctaAgregacionMasN1, saldoDeudor,saldoAcreedor, usuario);
    }

    public List<LogCarga> getLogCarga() {
        return mantenedorDAO.getLogCarga();
    }

    public JsonJTableExpenseBean addPeriodoInformeMant(Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {
        return mantenedorDAO.addPeriodoInformeMant(infId, perEjerId, perInfSec, usuario);
    }

    public JsonJTableExpenseBean updPeriodoInformeMant(Integer perInfId, Integer infId, Integer perEjerId, Integer perInfSec, String usuario) {
        return mantenedorDAO.updPeriodoInformeMant(perInfId, infId, perEjerId, perInfSec, usuario);
    }

    public JsonJTableExpenseBean delPeriodoInformeMant(Integer idPerInf, String usuario) {
        return mantenedorDAO.delPeriodoInformeMant(idPerInf, usuario);
    }

    public List<JsonJTableExpenseBean> getPeriodoInformeMant(Integer ejercicio, Integer informe, String order) {
        return mantenedorDAO.getPeriodoInformeMant(ejercicio, informe, order);
    }

    public JsonJTableExpenseBean addRolesPaginasMant(Integer rol, Integer pagina, String usuario) {
        return mantenedorDAO.addRolesPaginasMant(rol, pagina, usuario);
    }

    public JsonJTableExpenseBean updRolesPaginasMant(Integer rolPage, Integer rol, Integer pagina, String usuario) {
        return mantenedorDAO.updRolesPaginasMant(rolPage, rol, pagina, usuario);
    }

    public JsonJTableExpenseBean delRolesPaginasMant(Integer idPerInf, String usuario) {
        return mantenedorDAO.delRolesPaginasMant(idPerInf, usuario);
    }

    public List<JsonJTableExpenseBean> getRolesPaginasMant(Integer rol) {
        return mantenedorDAO.getRolesPaginasMant(rol);
    }

    public List<Rol> getRoles() {
        return entidadDAO.getRoles();
    }

    public List<OptionsJtable> loadPaginasOption() {
        return mantenedorDAO.loadPaginasOption();
    }

    public List<ProgramaPresupuestarioDTO> getPartida(Integer ejercicioId) {
        return mantenedorDAO.getPartida(ejercicioId);
    }

    public List<ProgramaPresupuestarioDTO> getCapitulo(String ejercicioId, Integer codPartida) {
        return mantenedorDAO.getCapitulo(ejercicioId, codPartida);
    }

    public List<ProgramaPresupuestarioDTO> getPP(Integer ejercicio, String partida, String capitulo) {
        return mantenedorDAO.getPP(ejercicio, partida, capitulo);
    }

    public Bitacora getRegistroBitacoraPartida(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraPartida(pid);
    }

    public Bitacora getRegistroBitacoraCapitulo(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraCapitulo(pid);
    }

    public Bitacora getRegistroBitacoraPrograma(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraPrograma(pid);
    }

    public Bitacora getRegistroBitacoraSector(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraSector(pid);
    }

    public Bitacora getRegistroBitacoraInstitucion(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraInstitucion(pid);
    }

    public Bitacora getRegistroBitacoraAT(Integer pid) {
        return mantenedorDAO.getRegistroBitacoraAT(pid);
    }

    public JsonJTableExpenseBean addPartida(Integer ejercicio, String codigo, String nombre, String usuario) {
        return mantenedorDAO.addPartida(ejercicio, codigo, nombre, usuario);
    }

    public JsonJTableExpenseBean getCodigoPartidaById(Integer id, String idEjercicio) {
        return mantenedorDAO.getCodigoPartidaById(id, idEjercicio);
    }

    public JsonJTableExpenseBean updCapitulo(Integer id, Integer idPartida, String codigo, String nombre, String ejercicio, String usuario) {
        return mantenedorDAO.updCapitulo(id, idPartida, codigo, nombre, ejercicio, usuario);
    }

    public JsonJTableExpenseBean getCodigoCapituloById(Integer id, String idEjercicio) {
        return mantenedorDAO.getCodigoCapituloById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getCodigoPartidaByIdCap(Integer id, String idEjercicio) {
        return mantenedorDAO.getCodigoPartidaByIdCap(id, idEjercicio);
    }

    public JsonJTableExpenseBean addPrograma(String codigo, String nombre, Integer id, String ejercicio, String usuario) {
        return mantenedorDAO.addPrograma(codigo, nombre, id, ejercicio, usuario);
    }

    public JsonJTableExpenseBean addCapitulo(String codCapitulo, String nomCapitulo, Integer id, String ejercicio, String usuario, Integer entidad) {
        return mantenedorDAO.addCapitulo(codCapitulo, nomCapitulo, id, ejercicio, usuario, entidad);
    }

    public JsonJTableExpenseBean getPartidaById(Integer id, String idEjercicio) {
        return mantenedorDAO.getPartidaById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getCapituloById(Integer id, String idEjercicio) {
        return mantenedorDAO.getCapituloById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getProgramaById(Integer id, String idEjercicio) {
        return mantenedorDAO.getProgramaById(id, idEjercicio);
    }

    public JsonJTableExpenseBean updPartida(Integer id, String nombre, String usuario, Integer idEjercicio) {
        return mantenedorDAO.updPartida(id, nombre, usuario, idEjercicio);
    }

    public JsonJTableExpenseBean updPrograma(Integer id, Integer idCapitulo, String codigo, String nombre, String ejercicio, String usuario) {
        return mantenedorDAO.updPrograma(id, idCapitulo, codigo, nombre, ejercicio, usuario);
    }

    public JsonJTableExpenseBean delPartida(Integer idPartida, String usuario) {
        return mantenedorDAO.delPartida(idPartida, usuario);
    }

    public JsonJTableExpenseBean delCapitulo(Integer idPartida, String usuario) {
        return mantenedorDAO.delCapitulo(idPartida, usuario);
    }

    public JsonJTableExpenseBean delPrograma(Integer idPartida, String usuario) {
        return mantenedorDAO.delPrograma(idPartida, usuario);

    }

    public List<AreaTransaccionalDTO> getAT(Integer ejercicio, String sector, String institucion) {
        return mantenedorDAO.getAT(ejercicio, sector, institucion);
    }

    public JsonJTableExpenseBean updArea(Integer ejercicio, Integer id, Integer idInstitucion,
                                         String codigoArea, String rut, String dv, String nombre, String usuario) {
        return mantenedorDAO.updArea(ejercicio, id, idInstitucion, codigoArea, rut, dv, nombre, usuario);
    }

    public JsonJTableExpenseBean updInstitucion(Integer id, String instCodigo, Integer sectorId, String nombre, String usuario, Integer ejercicio) {
        return mantenedorDAO.updInstitucion(id, instCodigo, sectorId, nombre, usuario, ejercicio);
    }

    public JsonJTableExpenseBean updSector(Integer id, String nombre, String usuario) {
        return mantenedorDAO.updSector(id, nombre, usuario);
    }

    public JsonJTableExpenseBean delSector(Integer idSector, String usuario) {
        return mantenedorDAO.delSector(idSector, usuario);
    }

    public JsonJTableExpenseBean delInstitucion(Integer idInstitucion, String usuario, Integer ejercicio) {
        return mantenedorDAO.delInstitucion(idInstitucion, usuario, ejercicio);
    }

    public JsonJTableExpenseBean delArea(Integer idInstitucion, Integer ejercicio, String usuario) {
        return mantenedorDAO.delArea(idInstitucion, ejercicio, usuario);
    }

    public JsonJTableExpenseBean getCodigoSectorById(Integer id) {
        return mantenedorDAO.getCodigoSectorById(id);
    }

    public JsonJTableExpenseBean getCodigoInstitucionById(Integer id, String idEjercicio) {
        return mantenedorDAO.getCodigoInstitucionById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getCodigoSectorByIdIns(Integer id, Integer idEjercicio) {
        return mantenedorDAO.getCodigoSectorByIdIns(id, idEjercicio);
    }

    public JsonJTableExpenseBean getInstitucionById(Integer id, String idEjercicio) {
        return mantenedorDAO.getInstitucionById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getAreaById(Integer id, String idEjercicio) {
        return mantenedorDAO.getAreaById(id, idEjercicio);
    }

    public JsonJTableExpenseBean getSectorById(Integer id) {
        return mantenedorDAO.getSectorById(id);
    }

    public JsonJTableExpenseBean addInstitucion(String codInstitucion, String nomInstitucion, String codigoPadre,Integer sector, String usuario, Integer ejercicio) {
        return mantenedorDAO.addInstitucion(codInstitucion, nomInstitucion, codigoPadre, sector, usuario, ejercicio);
    }

    public JsonJTableExpenseBean addSector(Integer ejercicio, String codigo, String nombre, String usuario) {
        return mantenedorDAO.addSector(ejercicio, codigo, nombre, usuario);
    }

    public JsonJTableExpenseBean addArea(String codigo, String nombre, String rut, String digitoV, String codigoSector,
                                             String codigoInstitucion, Integer institucion, String usuario, Integer ejercicio) {
        return mantenedorDAO.addArea(codigo, nombre, rut, digitoV, codigoSector, codigoInstitucion, institucion, usuario, ejercicio);
    }

    public List<AreaTransaccionalDTO> getSector(Integer ejercicioId) {
        return mantenedorDAO.getSector(ejercicioId);
    }

    public List<AreaTransaccionalDTO> getInstitucion(Integer ejercicioId, Integer codSector) {
        return mantenedorDAO.getInstitucion(ejercicioId, codSector);
    }

    public JsonJTableExpenseBean actualizarAsociacionCtasGastos(String idTabla1, String idTabla2,
                                                                String pnCtasGastosSelected, String userLogin) {
        return mantenedorDAO.actualizarAsociacionCtasGastos(idTabla1, idTabla2, pnCtasGastosSelected, userLogin);
    }

    public JsonJTableExpenseBean actualizarAsociacionCtasIngresos(String idTabla1,
                                                                  String idTabla2, String pnCtasIngresosSelected, String userLogin) {
        return mantenedorDAO.actualizarAsociacionCtasIngresos(idTabla1, idTabla2, pnCtasIngresosSelected, userLogin);
    }

    public JsonJTableExpenseBean editarCuentaContable(PlanCuentasContables cuentaContableDetalle2, String userConected, String idEjercicio) {
        return mantenedorDAO.editarCuentaContable(cuentaContableDetalle2, userConected, idEjercicio);
    }

    public JsonJTableExpenseBean actualizarPeriodosHabilitados(String idTabla1, String idTabla2,
                                                               String periodosHabilitados, String userConected) {
        return mantenedorDAO.actualizarPeriodosHabilitados(idTabla1, idTabla2, periodosHabilitados, userConected);
    }

    public RespuestaPair postAgregacion(PlanCuentaTbl registro) {
        return mantenedorDAO.postAgregacion(registro);
    }

    public JsonJTableExpenseBean postDesactivarCuentasContables(String userConected, String pAgr, String pN1,
                                                                String pN2, String pN3, String idEjercicio) {
        return mantenedorDAO.postDesactivarCuentasContables(userConected, pAgr, pN1, pN2, pN3, idEjercicio);
    }

    public List<CtasContablesSimples> getCtaContableByDesactivar(String pAgr, String pN1, String pN2, String pN3, String idEjercicio) {
        return mantenedorDAO.getCtaContableByDesactivar(pAgr, pN1, pN2, pN3, idEjercicio);
    }

    public List<PlanCuentaTitulo> getTitulosPlanCuenta(String idEjercicio) {
        return mantenedorDAO.getTitulosPlanCuenta(idEjercicio);
    }

    public List<PlanCuentaGrupo> getGruposPlanCuenta(String idEjercicio, String idTitulo) {
        return mantenedorDAO.getGruposPlanCuenta(idEjercicio, idTitulo);
    }

    public List<PlanCuentaSubGrupo> getSubGruposPlanCuenta(String idEjercicio, String idTitulo, String idGrupo) {
        return mantenedorDAO.getSubGruposPlanCuenta(idEjercicio, idTitulo, idGrupo);
    }

    public List<PlanCuentasContables> getPlanCuentasContable(String idEjercicio, String idTitulo, String idGrupo, String idSubGrupo, String isHabilitado) {
        return mantenedorDAO.getPlanCuentasContable(idEjercicio, idTitulo, idGrupo, idSubGrupo, isHabilitado);
    }

    public PlanCuentasContables getCtaContable(String idEjercicio, String idTabla1, String idTabla2) {
        return mantenedorDAO.getCtaContable(idEjercicio, idTabla1, idTabla2);
    }

    public CuentasPresupuestarias catalogoPresupuestario(String idEjercicio) {
        return mantenedorDAO.catalogoPresupuestario(idEjercicio);
    }

    public RespuestaPair crearCuentaContable(PlanCuentasContables ctaNueva, String user, String idEjercicio, Integer idFather) {
        return mantenedorDAO.crearCuentaContable(ctaNueva, user, idEjercicio, idFather);
    }

    public BitacoraCtaContableBO getRegistroBitacora(String idEjercicio,
                                                     String idTabla1,
                                                     String idTabla2) {
        return mantenedorDAO.getRegistroBitacora(idEjercicio, idTabla1, idTabla2);
    }
}

