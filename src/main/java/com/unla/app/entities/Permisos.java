package com.unla.app.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "permisos")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Permisos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int idPermiso;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPersona", nullable = false)
	protected Personas persona;
	
	@Column(name = "fecha")
	protected LocalDate fecha;
	
	@JoinTable(
			name = "permisoxlugar",
			joinColumns = @JoinColumn(name="FK_permiso", nullable = false),
			inverseJoinColumns = @JoinColumn(name="FK_lugar", nullable = false)
	)
	@ManyToMany(fetch = FetchType.LAZY)	
	@OrderBy("createAt")
	protected Set<Lugares> desdeHasta;
	
	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updatedat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Permisos() {
		
	}
	
	public Permisos(int idPermiso, Personas persona, LocalDate fecha, Set<Lugares> desdeHasta) {
		super();
		this.idPermiso = idPermiso;
		this.persona = persona;
		this.fecha = fecha;
		this.desdeHasta = desdeHasta;
	}

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public Personas getPersona() {
		return persona;
	}

	public void setPersona(Personas persona) {
		this.persona = persona;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Set<Lugares> getDesdeHasta() {
		return desdeHasta;
	}

	public void setDesdeHasta(Set<Lugares> desdeHasta) {
		this.desdeHasta = desdeHasta;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "{" +
			" idPermiso='" + getIdPermiso() + "'" +
			", persona='" + getPersona() + "'" +
			", fecha='" + getFecha() + "'" +
			", desdeHasta='" + getDesdeHasta() + "'" +
			", createAt='" + getCreateAt() + "'" +
			", updateAt='" + getUpdateAt() + "'" +
			"}";
	}
	
}
