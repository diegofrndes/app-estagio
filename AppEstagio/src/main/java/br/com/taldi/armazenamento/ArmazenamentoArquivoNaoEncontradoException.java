package br.com.taldi.armazenamento;

public class ArmazenamentoArquivoNaoEncontradoException extends ArmazenamentoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArmazenamentoArquivoNaoEncontradoException(String message) {
		super(message);
	}
	
	public ArmazenamentoArquivoNaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}

}
