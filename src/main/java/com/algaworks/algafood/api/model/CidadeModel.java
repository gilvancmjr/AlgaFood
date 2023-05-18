package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "cidades")
@Setter
@Getter
public class CidadeModel extends RepresentationModel<CidadeModel>{

	@ApiModelProperty(value = "ID da cidade", example = "1")
	private Long id;
	@ApiModelProperty(example = "Uberlândia")
	private String nome;
	private EstadoModel estado;
}
