package com.qualitysolutions.fresh_and_clean_web_app.modelos;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "Empresas")
@Audited

public class Empresa implements Serializable
{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "id_empresa")
 private Integer idEmpresa;
 @Column(nullable = false,length = 20,name = "rut_empresa")
 @NotEmpty
 private String rutEmpresa;
 @Column(nullable = false,length = 50,name = "razon_social")
 @NotEmpty
 private String razonSocial;
 @Column(nullable = false,length = 14,name = "telefono_empresa")
 @NotEmpty
 private String telefonoEmpresa;
 @Column(nullable = false,length = 60,name = "email_empresa")
 @NotEmpty
 private String emailEmpresa;
 @Column(nullable = false,length = 50,name = "rubro_empresa")
 @NotEmpty
 private String rubroEmpresa;


}
