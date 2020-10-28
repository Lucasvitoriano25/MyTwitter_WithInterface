package profile;

public class Pessoa_Fisica extends Perfil {
	private long cpf;

	public Pessoa_Fisica(String usuario) {
		super(usuario);
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

}
