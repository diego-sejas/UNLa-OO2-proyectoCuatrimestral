package com.unla.app.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.unla.app.converters.PermisoDiarioConverter;
import com.unla.app.converters.PermisoPeriodoConverter;
import com.unla.app.entities.Permisos;
import com.unla.app.entities.PermisosDiario;
import com.unla.app.entities.PermisosPeriodo;
import com.unla.app.models.LugarModel;
import com.unla.app.models.PermisoDiarioModel;
import com.unla.app.models.PermisoModel;
import com.unla.app.models.PermisoPeriodoModel;
import com.unla.app.models.PersonaModel;
import com.unla.app.models.RodadoModel;
import com.unla.app.repositories.IPermisoRepository;
import com.unla.app.services.IPermisoService;

@Service("permisosService")
public class PermisosServices implements IPermisoService{
	
	@Autowired
	@Qualifier("permisoDiarioConverter")
	private PermisoDiarioConverter permisoDiarioConverter;
	
	@Autowired
	@Qualifier("permisoPeriodoConverter")
	private PermisoPeriodoConverter permisoPeriodoConverter;
		
	@Autowired
	@Qualifier("permisoRepository")
	private IPermisoRepository permisoRepository;
	
	@Override
	public List<PermisoModel> findAll() {
		List<PermisoModel> permisos = new ArrayList<PermisoModel>();
		for(Permisos permiso : permisoRepository.findAll()) {
			if(permiso instanceof PermisosPeriodo)
				permisos.add(permisoPeriodoConverter.entityToModel((PermisosPeriodo)permiso));
			else if(permiso instanceof PermisosDiario)
				permisos.add(permisoDiarioConverter.entityToModel((PermisosDiario)permiso));
		}
			
		return permisos;
	}

	@Override
	public PermisoModel findById(int id) {
		Permisos permiso = permisoRepository.findById(id);
		if(permiso instanceof PermisosPeriodo)
			return permisoPeriodoConverter.entityToModel((PermisosPeriodo)permiso);
		else if(permiso instanceof PermisosDiario)
			return permisoDiarioConverter.entityToModel((PermisosDiario)permiso);
		
		return null;
	}
	
	@Override
	public PermisoModel insertOrUpdate(PermisoModel permisoModel) {
		if(permisoModel instanceof PermisoDiarioModel)
			return permisoDiarioConverter.entityToModel(
						permisoRepository.save(
									permisoDiarioConverter.modelToEntity((PermisoDiarioModel)permisoModel)
								)
					);
		else if(permisoModel instanceof PermisoPeriodoModel)
			return permisoPeriodoConverter.entityToModel(
						permisoRepository.save(
									permisoPeriodoConverter.modelToEntity((PermisoPeriodoModel)permisoModel)
								)
					);
		return null;
	}

	@Override
	public boolean remove(int id) {
		try {
			permisoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<PermisoDiarioModel> findByFechaBetween(LocalDate inicio, LocalDate fin) {
		List<PermisoDiarioModel> permisos = new ArrayList<PermisoDiarioModel>();
		for(PermisosDiario permisoDiario : permisoRepository.findByFechaBetween(inicio, fin))
			permisos.add(permisoDiarioConverter.entityToModel(permisoDiario));
		return permisos;
	}

	@Override
	public List<PermisoPeriodoModel> findByFecha(LocalDate inicio, LocalDate fin) {
		List<PermisoPeriodoModel> permisos = new ArrayList<PermisoPeriodoModel>();
		for(PermisosPeriodo permisoPeriodo : permisoRepository.findByFecha(inicio, fin))
			permisos.add(permisoPeriodoConverter.entityToModel(permisoPeriodo));
		return permisos;
	}
	
	
	public List<PermisoPeriodoModel> findByFechaAndLugar(LocalDate inicio, LocalDate fin, LugarModel lugar){
		List<PermisoPeriodoModel> permisoPeriodoModels = findByFecha(inicio, fin);
		List<PermisoPeriodoModel> list = new ArrayList<PermisoPeriodoModel>();
		
		for(PermisoPeriodoModel periodoModel : permisoPeriodoModels) {
			if(periodoModel.getDesdeHasta().get(0).equals(lugar) || periodoModel.getDesdeHasta().get(1).equals(lugar))
				list.add(periodoModel);
		}
		
		return list;
	}
	
	public List<PermisoDiarioModel> findByFechaBetweenAndLugar(LocalDate inicio, LocalDate fin, LugarModel lugar){
		List<PermisoDiarioModel> permisoDiarioModels = findByFechaBetween(inicio, fin);
		List<PermisoDiarioModel> list = new ArrayList<PermisoDiarioModel>();
		
		for(PermisoDiarioModel permisoDiarioModel : permisoDiarioModels) {
			System.out.println(permisoDiarioModel.getDesdeHasta());
			System.out.println(lugar);
			
			LugarModel desde = permisoDiarioModel.getDesdeHasta().get(0);
			LugarModel hasta = permisoDiarioModel.getDesdeHasta().get(1);
			
			if(desde.equals(lugar))
				list.add(permisoDiarioModel);
			else if(hasta.equals(lugar))
				list.add(permisoDiarioModel);
			
		}
		System.out.println(list);
		return list;
	}
	
	public List<PermisoPeriodoModel> findByDominio(RodadoModel rodadoModel) {
		List<PermisoPeriodoModel> permisos = new ArrayList<PermisoPeriodoModel>();
		for(PermisosPeriodo permisoPeriodo : permisoRepository.findByDominio(rodadoModel.getDominio()))
			permisos.add(permisoPeriodoConverter.entityToModel(permisoPeriodo));
		return permisos;
	}
	
	@Override
	public List<PermisoPeriodoModel> findByPersonaPeriodo(PersonaModel persona){
		List<PermisoPeriodoModel> aux = new ArrayList<PermisoPeriodoModel>();
		for(PermisosPeriodo permiso : permisoRepository.findByPersonaPeriodo(persona.getDni())) {
				aux.add(permisoPeriodoConverter.entityToModel(permiso));
		}
		return aux;
	}
	
	@Override
	public List<PermisoDiarioModel> findByPersonaDiario(PersonaModel persona){
		List<PermisoDiarioModel> aux = new ArrayList<PermisoDiarioModel>();
		for(PermisosDiario permisodia : permisoRepository.findByPersonaDiario(persona.getDni())) {
				aux.add(permisoDiarioConverter.entityToModel(permisodia));
		}
		return aux;
	}
	
}
