package exceptions;

public class SIException extends Exception {

	private static final long serialVersionUID = 1L;

//Exce��o repeti��o de usu�rio
	public SIException() {
		super("Nome de usu�rio repetido");
	}
}
