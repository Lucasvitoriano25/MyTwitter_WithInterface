package twitter;

import java.util.Vector;

import exceptions.MFPException;
import exceptions.PDException;
import exceptions.PEException;
import exceptions.PIException;
import exceptions.SIException;
import exceptions.UJCException;
import profile.Perfil;
import profile.Vector_Usuario;

public class MyTwitter implements ITwitter {

	Vector_Usuario Banco_de_Dados = new Vector_Usuario();

	@Override
	public void criarPerfil(Perfil usuario) throws PEException, UJCException {
		// Verfica se o usuário existe
		if (Banco_de_Dados.buscar(usuario.getUsuario()) == null) {
			Banco_de_Dados.cadastrar(usuario);
		} else {
			throw new PEException(usuario);
		}
	}

	@Override
	public void cancelarPerfil(String usuario) throws PIException, PDException {
		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		// Verifica se o usuário já está desativado
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		Banco_de_Dados.buscar(usuario).setAtivo(false);

	}

	@Override
	public void removerperfil(String usuario) throws PIException {
		// Remove o usuário do Twitter
		Banco_de_Dados.remover(usuario);

	}

	@Override
	public void tweetar(String usuario, String mensagem) throws PIException, MFPException, PDException {
		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		// Verifica o tamanho da mensagem
		if (mensagem.length() >= 140) {
			throw new MFPException();
		}
		Tweet tweet = new Tweet(usuario, mensagem);
		// Adiciona o tweet na timeline do autor
		Banco_de_Dados.buscar(usuario).addTweet(tweet);
		// Adiciona o tweet na timeline dos seguidores ativos
		for (Perfil obj : Banco_de_Dados.buscar(usuario).getSeguidores()) {
			if (obj.isAtivo() == true)
				obj.addTweet(tweet);
		}
	}

	@Override
	public Vector<Tweet> timeline(String usuario) throws PIException, PDException {
		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		Vector<Tweet> timeline = new Vector<>();
		// Retorna a timeline com os tweets dos usuários ativos
		for (Tweet obj : Banco_de_Dados.buscar(usuario).getTimeline()) {
			if (Banco_de_Dados.buscar(obj.getUsuario()).isAtivo() == true)
				timeline.add(obj);
		}
		return timeline;
	}

	@Override
	public Vector<Tweet> tweets(String usuario) throws PIException, PDException {
		Vector<Tweet> aux = new Vector<>();

		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		// Retorna os tweets do usuário
		for (Tweet obj : Banco_de_Dados.buscar(usuario).getTimeline()) {
			if (obj.getUsuario().equals(usuario)) {
				aux.add(obj);
			}
		}
		return aux;
	}

	@Override
	public void seguir(String seguidor, String seguido) throws SIException, PDException, PIException {
		if (Banco_de_Dados.buscar(seguidor) == null) {
			throw new PIException(seguidor);
		}
		if (Banco_de_Dados.buscar(seguido) == null) {
			throw new PIException(seguido);
		}
		if (Banco_de_Dados.buscar(seguido).isAtivo() == false) {
			throw new PDException(seguido);
		}
		if (Banco_de_Dados.buscar(seguidor).isAtivo() == false) {
			throw new PDException(seguidor);
		}
		if (seguidor.equals(seguido)) {
			throw new SIException();
		}
		// Condição para evitar que o usuário siga mais de uma vez a mesma pessoa
		if (!Banco_de_Dados.buscar(seguido).getSeguidores().contains(Banco_de_Dados.buscar(seguidor))) {
			Banco_de_Dados.buscar(seguido).addSeguidor(Banco_de_Dados.buscar(seguidor));
			Banco_de_Dados.buscar(seguidor).addSeguido(Banco_de_Dados.buscar(seguido));

		}
	}

	@Override
	public int numeroSeguidores(String usuario) throws PDException, PIException {

		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		// Retorna o numero de seguidores
		return Banco_de_Dados.buscar(usuario).getSeguidores().size();
	}

	@Override
	public Vector<Perfil> seguidores(String usuario) throws PDException, PIException {
		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		Vector<Perfil> seguidores = new Vector<>();
		// Adiciona ao vetor os seguidores ativos
		for (Perfil obj : Banco_de_Dados.buscar(usuario).getSeguidores()) {
			if (obj.isAtivo() == true)
				seguidores.add(obj);
		}
		return seguidores;
	}

	@Override
	public Vector<Perfil> seguidos(String usuario) throws PDException, PIException {
		if (Banco_de_Dados.buscar(usuario) == null) {
			throw new PIException(usuario);
		}
		if (Banco_de_Dados.buscar(usuario).isAtivo() == false) {
			throw new PDException(usuario);
		}
		Vector<Perfil> seguidos = new Vector<>();
		// Adiciona ao vetor os usuários ativos que o usuário segue
		for (Perfil obj : Banco_de_Dados.buscar(usuario).getSeguidos()) {
			if (obj.isAtivo() == true)
				seguidos.add(obj);
		}
		return seguidos;
	}

}
