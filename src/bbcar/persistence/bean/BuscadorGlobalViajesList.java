package bbcar.persistence.bean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bbcar.persistence.classes.Viaje;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class BuscadorGlobalViajesList implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String ciudadOrigen;
	protected String ciudadDestino;
	protected Date fechaHora;
	protected List<Viaje> viajes;

	public BuscadorGlobalViajesList() {
		buscar();
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void buscar() {
		viajes = BlaBlaCar.getInstance().buscarViajes(ciudadOrigen, ciudadDestino, fechaHora);
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

}
