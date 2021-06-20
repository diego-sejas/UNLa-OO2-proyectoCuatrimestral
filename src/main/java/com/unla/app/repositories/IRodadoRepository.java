package com.unla.app.repositories;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.unla.app.entities.Rodados;

@Repository("rodadoRepository")
public interface IRodadoRepository extends PagingAndSortingRepository<Rodados, Long> {
	
	Optional<Rodados> findByDominio(String dominio);

}
