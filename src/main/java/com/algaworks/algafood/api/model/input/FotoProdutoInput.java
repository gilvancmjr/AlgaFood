package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@ApiModelProperty(hidden = true)
    @NotNull
    @FileSize(max = "600KB")
    private MultipartFile arquivo;
	@ApiModelProperty(value = "Descrição da foto do produto", required = true)
    @NotBlank
    private String descricao;

}
