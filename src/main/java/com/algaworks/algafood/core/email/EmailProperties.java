package com.algaworks.algafood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {

	// Atribuimos FAKE como padrão
	// Isso evita o problema de enviar e-mails de verdade caso você esqueça
	// de definir a propriedade
	private Implementacao impl = Implementacao.FAKE;
	private Sandbox sandbox = new Sandbox();

	@NotNull
	private String remetente;

	public enum Implementacao {
		SMTP, FAKE, SANDBOX
	}
	
	@Getter
	@Setter
	public class Sandbox {
		
		private String destinatario;
		
	}

}
