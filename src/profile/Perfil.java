package profile;

import java.util.Vector;

import twitter.Tweet;

public abstract class Perfil {

	protected String usuario;
	protected Vector<Perfil> seguidos = new Vector<Perfil>();
	protected Vector<Perfil> seguidores = new Vector<Perfil>();
	protected Vector<Tweet> timeline = new Vector<Tweet>();
	protected Boolean ativo;

	public Perfil(String usuario) {
		this.usuario = usuario;
		ativo = true;
	}

	public void addSeguidor(Perfil usuario) {
		if (usuario != null) {
			seguidores.add(usuario);
		}
	}

	public void addSeguido(Perfil usuario) {
		if (usuario != null) {
			seguidos.add(usuario);
		}
	}

	public void addTweet(Tweet tweet) {
		if (tweet != null) {
			timeline.add(tweet);
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Vector<Perfil> getSeguidos() {
		return seguidos;
	}

	public Vector<Perfil> getSeguidores() {
		return seguidores;
	}

	public Vector<Tweet> getTimeline() {
		return timeline;
	}

	public Boolean isAtivo() {
		return ativo;
	}

}
