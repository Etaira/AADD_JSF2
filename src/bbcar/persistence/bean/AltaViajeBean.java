package bbcar.persistence.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Viaje;
import bbcar.persistence.dao.DAOException;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class AltaViajeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer numPlazas;
	private Double precio;
	private Parada origen;
	private Parada destino;
	private LoginSessionBean loginSessionBean;

	public AltaViajeBean() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		loginSessionBean = (LoginSessionBean) elContext.getELResolver().getValue(elContext, null, "loginSessionBean");
		Coche c = loginSessionBean.getUsuario().getCoche();
		if (c == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe registrar un coche", ""));
		} 
	}

	public void altaViaje() throws DAOException, IOException {
		Coche coche = loginSessionBean.getUsuario().getCoche();
		if (coche != null ) {
			Viaje viaje = BlaBlaCar.getInstance().registrarViaje(numPlazas, precio, coche.getId());
			if (viaje != null) {
				loginSessionBean.setViajeSeleccionado(viaje);
				FacesContext.getCurrentInstance().getExternalContext().redirect("formParadaOrigen.xhtml");
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema registrando el viaje", ""));
				FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Es necesario registrar un coche.", ""));
			FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
		}
	}

	public Integer getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(Integer numPlazas) {
		this.numPlazas = numPlazas;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Parada getOrigen() {
		return origen;
	}

	public void setOrigen(Parada origen) {
		this.origen = origen;
	}

	public Parada getDestino() {
		return destino;
	}

	public void setDestino(Parada destino) {
		this.destino = destino;
	}

	public LoginSessionBean getLoginSessionBean() {
		return loginSessionBean;
	}

	public void setLoginSessionBean(LoginSessionBean loginSessionBean) {
		this.loginSessionBean = loginSessionBean;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
