package bbcar.persistence.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
	private String calle;
	private Integer numero;
	private Integer CP;

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getCP() {
		return CP;
	}

	public void setCP(Integer cP) {
		CP = cP;
	}
}