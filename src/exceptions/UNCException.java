package exceptions;

import profile.Perfil;

public class UNCException extends Exception {

	private static final long serialVersionUID = 1L;

//Exce��o usuario n�o cadastrado
	public UNCException(Perfil usuario) {
		super("Usuario " + usuario.getUsuario() + " n�o existe");

	}
}