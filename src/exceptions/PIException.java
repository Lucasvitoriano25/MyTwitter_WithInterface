package exceptions;

public class PIException extends Exception {

	private static final long serialVersionUID = 1L;

//Exceção usuario não existente
	public PIException(String usuario) {
		super("O usuario " + usuario + " Não existe");
	}

}
