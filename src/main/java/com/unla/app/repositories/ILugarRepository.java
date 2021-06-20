package com.unla.app.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.unla.app.entities.Lugares;
import java.util.Optional;


@Repository("lugarRepository")
public interface ILugarRepository extends PagingAndSortingRepository<Lugares, Long>{  
	@Query(nativeQuery=true,value="Select * from lugares")
	public List<Lugares> traerTodos();
	
	@Query(nativeQuery=true,value="Select * from lugares where lugar.id_Lugar=(:id)")
	public abstract Lugares findById_(int id);
	
	@Query(nativeQuery=true,value="Select count(*) from lugares l where l.lugar=(:lugar)")
	public int repetido(String lugar);

	Optional<Lugares> findByCodigoPostal(String codigoPostal);
}
