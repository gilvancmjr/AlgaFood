package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.ItemPedido;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {

		// Mapeamento Especifico
		var modelMapper = new ModelMapper();
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
				.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		// modelMapper.createTypeMap(Restaurante.class,
		// RestauranteModel.class).addMapping(Restaurante::getTaxaFrete,
		// RestauranteModel::setTaxaFrete);
		// modelMapper.createTypeMap(Endereco.class,
		// EnderecoModel.class).addMapping(Endereco::getNomeEstadoDaCidade,
		// EnderecoModel::setNomeEstadoDaCidade);

		return new ModelMapper();
	}

}
