package exceptions;

public class PDException extends Exception {

	private static final long serialVersionUID = 1L;

//Exce��o usu�rio desativado
	public PDException(String usuario) {
		super("O perfil " + usuario + " Est� desativado");
	}
}
