package com.algaworks.algafood;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIT {

	private static final int COZINHA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCorretoCozinhaChinesa;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";

		jsonCorretoCozinhaChinesa = ResourceUtils.getContentFromResource("/json/correto/cozinha-chinesa.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveRetornarQuantidadeCorretaDeCozinhas_QuandoConsultarCozinhas() {
		RestAssured.given().accept(ContentType.JSON).when().get().then().body("",
				Matchers.hasSize(quantidadeCozinhasCadastradas));
	}
	
	//testes

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given().body(jsonCorretoCozinhaChinesa).contentType(ContentType.JSON).accept(ContentType.JSON)
				.when().post().then().statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarCozinhaExistente() {
		RestAssured.given().pathParam("cozinhaId", cozinhaAmericana.getId()).accept(ContentType.JSON).when()
				.get("/{cozinhaId}").then().statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
		RestAssured.given().pathParam("cozinhaId", COZINHA_ID_INEXISTENTE).accept(ContentType.JSON).when()
				.get("/{cozinhaId}").then().statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Cozinha cozinhaTailandesa = new Cozinha();
		cozinhaTailandesa.setNome("Tailandesa");
		cozinhaRepository.save(cozinhaTailandesa);

		cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();
	}

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
