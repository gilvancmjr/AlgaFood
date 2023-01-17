package com.algaworks.algafood.domain.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

import lombok.Builder;
import lombok.Getter;

@Service
public interface FotoStorageService {

    void armazenar(NovaFoto novaFoto);

    @Builder
    @Getter
    class NovaFoto{
        private String nomeArquivo;
        private InputStream inputStream;
    }
    
}
