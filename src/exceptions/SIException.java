package exceptions;

public class SIException extends Exception {

	private static final long serialVersionUID = 1L;

//Exceção repetição de usuário
	public SIException() {
		super("Nome de usuário repetido");
	}
}
