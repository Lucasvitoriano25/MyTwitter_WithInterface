package exceptions;

public class PDException extends Exception {

	private static final long serialVersionUID = 1L;

//Exceção usuário desativado
	public PDException(String usuario) {
		super("O perfil " + usuario + " Está desativado");
	}
}
