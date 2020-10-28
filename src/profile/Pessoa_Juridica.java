package profile;

public class Pessoa_Juridica extends Perfil {
	private long cnpj;

	public Pessoa_Juridica(String usuario) {
		super(usuario);
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

}
