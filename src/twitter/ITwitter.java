package twitter;

import java.util.Vector;

import exceptions.MFPException;
import exceptions.PDException;
import exceptions.PEException;
import exceptions.PIException;
import exceptions.SIException;
import exceptions.UJCException;
import profile.Perfil;

public interface ITwitter {

	public void criarPerfil(Perfil usuario) throws PEException, UJCException;

	public void cancelarPerfil(String usuario) throws PIException, PDException;

	public void tweetar(String usuario, String mensagem) throws PIException, MFPException, PDException;

	public Vector<Tweet> timeline(String usuario) throws PIException, PDException;

	public Vector<Tweet> tweets(String usuario) throws PIException, PDException;

	public void seguir(String seguidor, String seguido) throws PIException, PDException, SIException;

	public int numeroSeguidores(String usuario) throws PDException, PIException;

	public Vector<Perfil> seguidores(String usuario) throws PDException, PIException;

	public Vector<Perfil> seguidos(String usuario) throws PDException, PIException;

	public void removerperfil(String usuario) throws PIException;

}
