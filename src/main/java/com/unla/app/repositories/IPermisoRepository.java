package com.unla.app.repositories;

import java.time.LocalDate;
import java.util.List;

import com.unla.app.entities.Permisos;
import com.unla.app.entities.PermisosDiario;
import com.unla.app.entities.PermisosPeriodo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository("permisoRepository")
public interface IPermisoRepository extends JpaRepository<Permisos, Integer> {
    
	public abstract Permisos findById(int id);

	@Query(value = "select * from permiso p "
			+ "inner join PermisosPeriodo pp on p.id_permiso=pp.id_permiso "
			+ "where p.fecha between ?1 and ?2 "
			+ "or "
			+ "date_add(p.fecha, interval pp.cant_dias day) between ?1 and ?2", nativeQuery = true)
	public abstract List<PermisosPeriodo> findByFecha(@Param("inicio") LocalDate inicio, @Param("fin")LocalDate fin);
	
	@Query("SELECT pd FROM PermisosDiario pd "
			+ "inner join fetch pd.persona "
			+ "WHERE pd.fecha BETWEEN :inicio AND :fin")
	public abstract List<PermisosDiario> findByFechaBetween(LocalDate inicio, LocalDate fin);
	
	@Query(value="SELECT * from Permisos p"
			+" inner join PermisosPeriodo pp on p.id_permiso=pp.id_permiso"
			+" inner join Rodados r ON r.id_rodado=pp.id_rodado "
			+ "WHERE r.dominio=(:dominio)",nativeQuery=true)
	public abstract List<PermisosPeriodo> findByDominio(@Param("dominio") String dominio);
	
	@Query(value="SELECT * FROM Permisosdiario pd"
	        +" INNER JOIN Permisos p ON p.id_permiso=pd.id_permiso"
	        +" INNER JOIN Personas pe ON pe.id_persona=p.id_persona"
			+" WHERE pe.dni=(:dni)",nativeQuery=true)
	public abstract List<PermisosDiario> findByPersonaDiario(int dni);
	
	@Query(value="SELECT * FROM Permisosperiodo pp"
	        +" INNER JOIN Permisos p ON p.id_permiso=pp.id_permiso"
	        +" INNER JOIN Personas pe ON pe.id_persona=p.id_persona"
			+" WHERE pe.dni=(:dni)",nativeQuery=true)
	public abstract List<PermisosPeriodo> findByPersonaPeriodo(int dni);
	
	
}