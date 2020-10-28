package profile;

import exceptions.PIException;
import exceptions.UJCException;
import exceptions.UNCException;

public interface IRepositorioUsuario {
	public void cadastrar(Perfil usuario) throws UJCException;

	public Perfil buscar(String usuario);

	public void atualizar(Perfil usuario) throws UNCException;

	public void remover(String usuario) throws PIException;
}
