package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel>{

    @Autowired
    private ModelMapper modelMapper;
    
    public EstadoModelAssembler() {
        super(EstadoController.class, EstadoModel.class);
    }
    
    @Override
    public EstadoModel toModel(Estado estado) {
        EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
        modelMapper.map(estado, estadoModel);
        
        estadoModel.add(linkTo(EstadoController.class).withRel("estados"));
        
        return estadoModel;
    }
    
    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(EstadoController.class).withSelfRel());
    }
    
}
