package profile;

import java.util.Vector;

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
			if (obj.getUsuario().equals(usuario.getUsuario())) {
				obj = usuario;
				aux = true;
			}
		}
		if (aux == false) {
			throw new UNCException(usuario);
		}
	}

}
