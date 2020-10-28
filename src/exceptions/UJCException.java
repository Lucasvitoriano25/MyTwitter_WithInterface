package exceptions;

import profile.Perfil;

public class UJCException extends Exception {

	private static final long serialVersionUID = 1L;

//Exceção usuário já cadastrado
	public UJCException(Perfil usuario) {
		super("Usuario " + usuario.getUsuario() + " já existe");
	}
}
