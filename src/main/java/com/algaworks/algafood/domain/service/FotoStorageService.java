package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;

@Service
public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);

	void remover(String nomeArquivo);

	default void subistituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	@Builder
	@Getter
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
	}

}
