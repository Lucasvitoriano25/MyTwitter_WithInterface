package profile;

import java.util.Vector;

import exceptions.PIException;
import exceptions.UJCException;
import exceptions.UNCException;

public class Vector_Usuario implements IRepositorioUsuario {

	Vector<Perfil> vetor = new Vector<Perfil>();

	public void cadastrar(Perfil usuario) throws UJCException {
		if (vetor.contains(usuario)) {
			throw new UJCException(usuario);
		} else {
			vetor.add(usuario);
		}
	}

	@Override
	public Perfil buscar(String usuario) {
		for (Perfil obj : vetor) {
			if (obj.getUsuario().equals(usuario)) {
				return obj;
			}
		}
		return null;

	}

	@Override
	// MUDAR ATUALIZAR
	public void atualizar(Perfil usuario) throws UNCException {
		Boolean aux = false;
		for (Perfil obj : vetor) {
			if (obj.getUsuario() == usuario.getUsuario()) {
				obj = usuario;
				aux = true;
			}
		}
		if (aux == false) {
			throw new UNCException(usuario);
		}
	}

	@Override
	public void remover(String usuario) throws PIException {
		Boolean aux = false;
		Perfil momentaneo = null;
		for (Perfil obj : vetor) {
			if (obj.getUsuario().equals(usuario)) {
				aux = true;
				momentaneo = obj;
			}
		}
		if (aux == true) {
			vetor.remove(momentaneo);
		}
		if (aux == false) {
			throw new PIException(usuario);
		}
	}

}
