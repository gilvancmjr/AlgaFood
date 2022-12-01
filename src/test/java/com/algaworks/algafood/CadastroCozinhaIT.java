package com.algaworks.algafood;

import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	// TESTE DE API

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();// mostra o retorno do teste
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter4Cozinhas_QuandoConsultarCozinhas() {

		RestAssured.given().accept(ContentType.JSON).when().get().then().body("", Matchers.hasSize(4)).body("nome",
				Matchers.hasItems("Indiana", "Tailandesa"));
	}

	// TESTE DE INTEGRAÇÃO

//	@Autowired
//	CadastroCozinhaService cadastroCozinhaService;
//
//	@Test
//	public void testarCadastroCozinhaComSucesso() {
//
//		// cenário
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome("Chinesa");
//
//		// Ação
//		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
//
//		// validação
//		assertThat(novaCozinha).isNotNull();
//		assertThat(novaCozinha.getId()).isNotNull();
//
//	}
//
//	@Test
//	public void testarCadastroCozinhaSemNome() {
//		Cozinha novaCozinha = new Cozinha();
//		novaCozinha.setNome(null);
//
//		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
//			cadastroCozinhaService.salvar(novaCozinha);
//		});
//
//		assertThat(erroEsperado).isNotNull();
//	}
//
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
//
//		EntidadeEmUsoException erroEsperado = Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
//			cadastroCozinhaService.excluir(1L);
//		});
//		assertThat(erroEsperado).isNotNull();
//
//	}
//
//	@Test
//	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
//
//		CozinhaNaoEncontradaException erroEsperado = Assertions.assertThrows(CozinhaNaoEncontradaException.class,
//				() -> {
//					cadastroCozinhaService.excluir(10L);
//				});
//		assertThat(erroEsperado).isNotNull();
//
//	}

}
