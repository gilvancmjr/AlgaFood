package com.algaworks.algafood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.model.Permissao;
@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

	

}
