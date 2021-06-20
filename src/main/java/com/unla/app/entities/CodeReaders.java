package com.unla.app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="codeReaders")
@DynamicInsert(true)
@DynamicUpdate(true)
public class CodeReaders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idQr;
	
	@Column(name = "code", nullable = false)
	private String code;
		
    @Column(name = "permisoId", nullable = false)
	private Long permisoId;

	@Column(name = "createdat")
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@Column(name = "updatedat") 
	@UpdateTimestamp
	private LocalDateTime updateAt;
	
	public CodeReaders(){}

	public CodeReaders(long idQr, String code, long permisoId) {
		super();
		this.idQr = idQr;
		this.code = code;
        this.permisoId = permisoId;		
	}

    public CodeReaders(String code, long permisoId) {
		super();
		this.code = code;	
        this.permisoId = permisoId;	
	}


    public long getIdQr() {
        return this.idQr;
    }

    public void setIdQr(long idQr) {
        this.idQr = idQr;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getPermisoId() {
        return this.permisoId;
    }

    public void setPermisoId(Long permisoId) {
        this.permisoId = permisoId;
    }

    public LocalDateTime getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "{" +
            " idQr='" + getIdQr() + "'" +
            ", code='" + getCode() + "'" +
            ", permisoId='" + getPermisoId() + "'" +
            ", createAt='" + getCreateAt() + "'" +
            ", updateAt='" + getUpdateAt() + "'" +
            "}";
    }
  

}