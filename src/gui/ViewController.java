package gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import exceptions.MFPException;
import exceptions.PDException;
import exceptions.PEException;
import exceptions.PIException;
import exceptions.SIException;
import exceptions.UJCException;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import profile.Perfil;
import profile.Pessoa_Fisica;
import profile.Pessoa_Juridica;
import twitter.MyTwitter;
import twitter.Tweet;

public class ViewController implements Initializable {

	MyTwitter Twitter = new MyTwitter();

	@FXML
	private TextArea txtTela;

	@FXML
	private TextArea txtLeitor;

	@FXML
	private TextField txtUsuario;

	@FXML
	private Label txtUsuarioLogado;

	@FXML
	private Button btLogin;

	@FXML
	private Button btLogout;

	@FXML
	private Button btTweets;

	@FXML
	private Button btTimeline;

	@FXML
	private Button btTweetar;

	@FXML
	private Button btNdeSeguidores;

	@FXML
	private Button btSeguidores;

	@FXML
	private Button btSeguidos;

	@FXML
	private Button btSeguir;

	@FXML
	private Button btCadastrar;

	@FXML
	private Button btCancelar;

	private String usuarioLogado = null;

	@FXML
	public void onBtLoginAction() {
		if (usuarioLogado == null) {
			usuarioLogado = txtUsuario.getText();
			txtUsuario.clear();
			txtUsuarioLogado.setText(usuarioLogado);
			txtTela.setText("Para \r\n" + "Tweetar : Digite a mensagem \r\n"
					+ "Para trocar de usuário: Coloque o \r\n usuário ojetivo \r\n"
					+ "Para seguir: Coloque o usuário \r\n objetivo");

		} else
			txtTela.setText("De logout primeiro");
	}

	@FXML
	public void onBtLogoutAction() {
		usuarioLogado = null;
		txtUsuarioLogado.setText("");
		printTelainicial("");
	}

	@FXML
	public void onBtTweetsAction() {
		String aux = "Seus tweets: \r\n";
		try {
			Vector<Tweet> tweet = Twitter.tweets(usuarioLogado);
			for (Tweet obj : tweet) {
				aux += (obj.getMensagem()) + "\r\n";
			}
			txtTela.setText(aux);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}

	}

	@FXML
	public void onBtTimelineAction() {
		String aux = "Sua timeline: \r\n";
		try {
			Vector<Tweet> tweet = Twitter.timeline(usuarioLogado);
			for (Tweet obj : tweet) {
				aux += (obj.getUsuario() + ": \r\n" + obj.getMensagem() + " \r\n");
			}
			txtTela.setText(aux);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtTweetarAction() {
		String mensagem = txtLeitor.getText();
		txtLeitor.clear();
		try {
			Twitter.tweetar(usuarioLogado, mensagem);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (MFPException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtNdeSeguidoresAction() {
		try {
			int seguidores = Twitter.numeroSeguidores(usuarioLogado);
			txtTela.setText("Você tem: " + seguidores + " seguidores");
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtSeguidoresAction() {
		String aux = "Seus seguidores :\r\n";
		try {
			Vector<Perfil> perfil = Twitter.seguidores(usuarioLogado);
			for (Perfil obj : perfil) {
				aux += obj.getUsuario() + "\r\n";
			}
			txtTela.setText(aux);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtSeguidosAction() {
		String aux = "Quem você segue :\r\n";
		try {
			Vector<Perfil> perfil = Twitter.seguidos(usuarioLogado);
			for (Perfil obj : perfil) {
				aux += obj.getUsuario() + "\r\n";
			}
			txtTela.setText(aux);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtSeguirAction() {
		
		String usuarioSeguir = txtUsuario.getText();
		txtUsuario.clear();
		try {
			Twitter.seguir(usuarioLogado, usuarioSeguir);
			txtTela.setText("Você seguiu: " + usuarioSeguir);
		} catch (SIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PDException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		} catch (PIException e) {
			Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void onBtCadastrarAction() {
		if (usuarioLogado == null) {
			String usuario = txtUsuario.getText();
			txtUsuario.clear();

			String perfilType = txtLeitor.getText();
			txtLeitor.clear();
			if (perfilType.equals("PF")) {
				Pessoa_Fisica perfil = new Pessoa_Fisica(usuario);
				try {
					Twitter.criarPerfil(perfil);
					printTelainicial("Perfil " + usuario + " Criado \r\n");
				} catch (PEException e) {
					Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);

				} catch (UJCException e) {
					Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
				}

			} else if (perfilType.equals("PJ")) {
				Pessoa_Juridica perfil = new Pessoa_Juridica(usuario);
				try {
					Twitter.criarPerfil(perfil);
					printTelainicial("Perfil " + usuario + " Criado \r\n");
				} catch (PEException e) {
					Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);

				} catch (UJCException e) {
					Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
				}
			} else {
				printTelainicial("Informe se é PF ou PJ");
			}
		} else
			txtTela.setText("De logout primeiro");
	}

	@FXML
	public void onBtTimeCancelarAction() {
		if (usuarioLogado != null) {
			String usuario = usuarioLogado;
			txtUsuario.clear();
			try {
				Twitter.cancelarPerfil(usuario);
				printTelainicial("Perfil " + usuario + " Cancelado \r\n");

			} catch (PIException e) {
				Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);

			} catch (PDException e) {
				Alerts.showAlert("Entrada inválida", null, e.getMessage(), AlertType.ERROR);
			}
		} else
			txtTela.setText("De Login primeiro");
	}

	private void printTelainicial(String text) {
		txtTela.setText(text + "Para \r\n" + "Login: Usuário\r\n" + "Cadastrar : Usuário e PF/PJ\r\n"
				+ "Cancelar: Usuário\r\n");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
