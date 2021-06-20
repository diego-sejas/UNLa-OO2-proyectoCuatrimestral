package com.unla.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="lugares")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Lugares {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idLugar;
	
	@Column(name = "lugar", nullable = false)
	private String lugar;
	
	@Column(name = "codigoPostal", nullable = false)
	private String codigoPostal;
	
	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updatedat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public Lugares(){}

	public Lugares(long idLugar, String lugar, String codigoPostal) {
		super();
		this.idLugar = idLugar;
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}

	public Lugares(String lugar, String codigoPostal) {
		this.lugar = lugar;
		this.codigoPostal = codigoPostal;
	}


	public long getIdLugar() {
		return idLugar;
	}


	protected void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public boolean equals(Lugares lugar) {
		return codigoPostal.equalsIgnoreCase(lugar.codigoPostal) && this.lugar.equalsIgnoreCase(lugar.lugar);
	}

	@Override
	public String toString() {
		return "{" +
			" idLugar='" + getIdLugar() + "'" +
			", lugar='" + getLugar() + "'" +
			", codigoPostal='" + getCodigoPostal() + "'" +
			"}";
	}

	public String toJson(Lugares lugar) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(lugar);
		return jsonString;
	}
}