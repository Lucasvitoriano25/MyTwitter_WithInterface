package exceptions;

import profile.Perfil;

public class PEException extends Exception {

	private static final long serialVersionUID = 1L;

//Excecão perfil já existente
	public PEException(Perfil usuario) {
		super("Nome de usuário " + usuario.getUsuario() + " já existe");
	}

}
