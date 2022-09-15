package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.exception.EntidadeEmUsoException;
import com.algaworks.algafood.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.model.Cidade;
import com.algaworks.algafood.repository.CidadeRepository;
import com.algaworks.algafood.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@GetMapping("/cidade")
	public List<Cidade> lista() {
		return cidadeRepository.findAll();

	}

	 @GetMapping("/{cidadeId}")
		public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
			
			if (cidade.isPresent()) {
				return ResponseEntity.ok(cidade.get());
			}
			
			return ResponseEntity.notFound().build();
		}

	  @PostMapping
		public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
			try {
				cidade = cadastroCidade.salvar(cidade);
				
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(cidade);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		}
	  @PutMapping("/{cidadeAId}")
		public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId,
				@RequestBody Cidade cidade) {
		  Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
			
			if (cidadeAtual != null) {
				BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
				
				Cidade	cidadeSalva = cadastroCidade.salvar(cidadeAtual.get());
				return ResponseEntity.ok(cidadeSalva);
			}
			
			return ResponseEntity.notFound().build();
		}
	  @DeleteMapping("/{cidadeAId}")
		public ResponseEntity<?> remover(@PathVariable Long cidadeAId) {
			try {
				cadastroCidade.excluir(cidadeAId);	
				return ResponseEntity.noContent().build();
				
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.notFound().build();
				
			} catch (EntidadeEmUsoException e) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(e.getMessage());
			}
		}
	    
}
