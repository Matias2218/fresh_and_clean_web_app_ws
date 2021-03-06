package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import com.qualitysolutions.fresh_and_clean_web_app.dao.*;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioServicioImpl implements IUsuarioServicio {

    @Autowired
    IEmpleadoDao empleadoDao;
    @Autowired
    IBoletaDao boletaDao;
    @Autowired
    ITipoEmpleadoDao tipoEmpleadoDao;
    @Autowired
    IPersonaDao personaDao;
    @Autowired
    IPeticionHoraDao peticionHoraDao;
    @Autowired
    IServicioDao servicioDao;
    @Autowired
    IClienteDao clienteDao;

    @Override
    @Transactional
    public PeticionHora savePeticion(PeticionHora peticionHora) {
         return peticionHoraDao.save(peticionHora);
    }

    @Override
    @Transactional(readOnly = true)
    public PeticionHora findByIdPeticion(Integer id) {
        return peticionHoraDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PeticionHora> findAllPeticionHoras(Pageable pageable,Integer idEmpleado) {
        return peticionHoraDao.findAllFechaIgualMayor(pageable,idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PeticionHora> findAllPeticionHorasEspera(Pageable pageable, Integer idEmpleado) {
        return peticionHoraDao.findAllFechaIgualMayorEspera(pageable,idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PeticionHora> findByPeticionHorasEspera(Pageable pageable, Integer idEmpleado, String horaAtencion) {
        return peticionHoraDao.findByFechaIgualMayorEspera(pageable,idEmpleado,horaAtencion);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PeticionHora> findByFechaIgualMayorAceptada(Pageable pageable, Integer idEmpleado, String horaAtencion) {
        return peticionHoraDao.findByFechaIgualMayorAceptada(pageable,idEmpleado,horaAtencion);
    }

    @Override
    @Transactional
    public Boolean rechazarHora(Integer id) {
        peticionHoraDao.rechazarHora(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean horaRelizada(Integer id) {
        peticionHoraDao.horaRealizada(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean horaAceptada(Integer id) {
        peticionHoraDao.horaAceptada(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> findAllServicio() {
        return (List<Servicio>) servicioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> findAllServicioById(ArrayList<Integer> ids) {
        return (List<Servicio>) servicioDao.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAllCliente() {
        return (List<Cliente>) clienteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllHorasOcupadas(LocalDate fecha, Integer idEmpleado) {
        return peticionHoraDao.barberosHoras(fecha,idEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAllEmpleadoOrderByEstaActivo() {
        return empleadoDao.findAllByOrderByEstaActivoDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoEmpleado> findAllTipoEmpleados() {
        return (List<TipoEmpleado>) tipoEmpleadoDao.findAll();
    }

    @Override
    @Transactional
    public Empleado saveEmpleado(Empleado empleado) {
        return empleadoDao.save(empleado);
    }

    @Override
    @Transactional
    public Boolean activeEmpleado(Integer id) {
        empleadoDao.activeEmpleado(id);
        return true;
    }

    @Override
    @Transactional
    public Boolean desactiveEmpleado(Integer id) {
        empleadoDao.desactiveEmpleado(id);
        return true;
    }

    @Override
    public void deleteEmpleadoById(Integer id) {
        empleadoDao.deleteById(id);
    }

    @Override
    public List<Empleado> findAllByTipoEmpleado() {
        TipoEmpleado tipoEmpleado = new TipoEmpleado();
        tipoEmpleado.setIdTipo(1);
        return empleadoDao.findAllByTipoEmpleado(tipoEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Integer id) {
        return empleadoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> findAllBoletas() {
        return (List<Boleta>) boletaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Integer> findAllDistinctBoletas() {
        return boletaDao.findAllDistinctBoletas();
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String,String> findAllMesesBoletas() {
        Map<String,String> mesesReturn = new HashMap<>();
        List<Object[]> meses = boletaDao.findAllMesesBoletas();
        meses.stream().forEach(o -> {
            mesesReturn.put(o[0].toString(),o[1].toString());
        });
        return mesesReturn;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String[]> barberoConMasAtenciones() {
        return personaDao.barberoConMasAntenciones();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Boleta> findAllByAnoAndByMes(Integer año,Integer mes) {
        return boletaDao.findAllByAnoAndByMes(año,mes);
    }

    @Override
    @Transactional
    public Boleta saveBoleta(Boleta boleta) {
        return boletaDao.save(boleta);
    }
}
