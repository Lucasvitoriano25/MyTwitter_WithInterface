package exceptions;

import profile.Perfil;

public class PEException extends Exception {

	private static final long serialVersionUID = 1L;

//Excec�o perfil j� existente
	public PEException(Perfil usuario) {
		super("Nome de usu�rio " + usuario.getUsuario() + " j� existe");
	}

}
