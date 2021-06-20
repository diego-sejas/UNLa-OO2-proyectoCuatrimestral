package com.unla.app.services;

import java.time.LocalDate;
import java.util.List;

import com.unla.app.models.PermisoDiarioModel;
import com.unla.app.models.PermisoModel;
import com.unla.app.models.PermisoPeriodoModel;
import com.unla.app.models.PersonaModel;
import com.unla.app.models.RodadoModel;

public interface IPermisoService {
	public List<PermisoModel> findAll();
	public PermisoModel findById(int id);
	
	public List<PermisoDiarioModel> findByFechaBetween(LocalDate inicio, LocalDate fin);
	public List<PermisoPeriodoModel> findByFecha(LocalDate inicio, LocalDate fin);
	
	public List<PermisoPeriodoModel> findByDominio(RodadoModel rodadoModel);
	public List<PermisoPeriodoModel> findByPersonaPeriodo(PersonaModel persona);
	public List<PermisoDiarioModel> findByPersonaDiario(PersonaModel persona);
	
	public PermisoModel insertOrUpdate(PermisoModel permisoModel);
	public boolean remove(int id);
	
}
