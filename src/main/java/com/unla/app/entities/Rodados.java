 package com.unla.app.entities;

 import java.time.LocalDateTime;
 import javax.persistence.Column;
 import javax.persistence.Entity;
 import javax.persistence.GeneratedValue;
 import javax.persistence.GenerationType;
 import javax.persistence.Id;

 import org.hibernate.annotations.CreationTimestamp;
 import org.hibernate.annotations.UpdateTimestamp;

 @Entity(name="rodados")
 public class Rodados {
 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long idRodado;
 	
 	@Column(name="dominio", nullable=false, length=45)
 	private String dominio;
 	
 	@Column(name="vehiculo", nullable=false, length=45)
 	private String vehiculo;
 	
 	/*@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="rodado")
 	private Set<PermisosPeriodo> permisosPeriodo;*/
 	
 	@Column(name="createdat")
 	@CreationTimestamp
 	private LocalDateTime createdAt;
 	
 	@Column(name="updatedat")
 	@UpdateTimestamp
 	private LocalDateTime updatedAt;

 	public Rodados() {}

	public Rodados(Long idRodado, String dominio, String vehiculo, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Rodados(long idRodado, String dominio, String vehiculo) {
		super();
		this.idRodado = idRodado;
		this.dominio = dominio;
		this.vehiculo = vehiculo;
    }

    public Long getIdRodado() {
		return idRodado;
	}

	public void setIdRodado(Long idRodado) {
		this.idRodado = idRodado;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Rodados [idRodado=" + idRodado + ", dominio=" + dominio + ", vehiculo=" + vehiculo + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}


 }