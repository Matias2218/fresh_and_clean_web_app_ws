package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Servicios")
@Audited
public class Servicio implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Integer idServicio;
    @Column(name = "nombre_servicio",nullable = false,length = 40)
    private String nombreServicio;
    @Column(name = "descripcion_servicio",nullable = false,length = 140)
    private String descripcionServicio;
    @Column(name = "precio_servicio",nullable = false)
    private Integer precioServicio;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sucursal")
    private Sucursal sucursal;

    public Servicio() {
    }

    public Servicio(String nombreServicio, String descripcionServicio, Integer precioServicio, Sucursal sucursal) {
        this.nombreServicio = nombreServicio;
        this.descripcionServicio = descripcionServicio;
        this.precioServicio = precioServicio;
        this.sucursal = sucursal;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public Integer getPrecioServicio() {
        return precioServicio;
    }

    public void setPrecioServicio(Integer precioServicio) {
        this.precioServicio = precioServicio;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
