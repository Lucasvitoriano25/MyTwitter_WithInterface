package exceptions;

public class MFPException extends Exception {

	private static final long serialVersionUID = 1L;

//Exceção caso seja maior que 140 caracteres
	public MFPException() {
		super("Limite de 140 caracteres atingido");
	}
}
