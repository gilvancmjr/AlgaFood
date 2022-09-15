package com.algaworks.algafood.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> findByNomeContaining(String nome);
//	ou
//	List<Cozinha> findConsultaByNome(String nome);
//	ou
//	List<Cozinha> nome(String nome);

	Optional<Cozinha> nome(String nome);

}
