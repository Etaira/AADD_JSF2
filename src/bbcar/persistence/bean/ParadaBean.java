package bbcar.persistence.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Viaje;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class ParadaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer IdViaje;
	private String ciudadOrigen;
	private String calleOrigen;
	private Integer numeroOrigen;
	private Integer CPOrigen;
	private Date fechaOrigen;
	private String ciudadDestino;
	private String calleDestino;
	private Integer numeroDestino;
	private Integer CPDestino;
	private Date fechaDestino;
	private LoginSessionBean loginSessionBean;

	public ParadaBean() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		loginSessionBean = (LoginSessionBean) elContext.getELResolver().getValue(elContext, null, "loginSessionBean");
		Viaje viaje = loginSessionBean.getViajeSeleccionado();
		if (viaje == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error, se creo el viaje correctamente", ""));
		}

		IdViaje = viaje.getId();
	}

	public void altaParadaOrigen() {
		Parada parada = BlaBlaCar.getInstance().registrarParadaOrigen(IdViaje, ciudadOrigen, calleOrigen, numeroOrigen,
				CPOrigen, fechaOrigen);
		if (parada != null)
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("formParadaDestino.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		else {
			try {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la parada", ""));
				FacesContext.getCurrentInstance().getExternalContext().redirect("formParadaOrigen.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void altaParadaDestino() {
		Parada parada = BlaBlaCar.getInstance().registrarParadaDestino(IdViaje, ciudadDestino, calleDestino,
				numeroDestino, CPDestino, fechaDestino);
		if (parada != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la parada", ""));
				FacesContext.getCurrentInstance().getExternalContext().redirect("formParadaDestino.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Integer getIdViaje() {
		return IdViaje;
	}

	public void setIdViaje(Integer idViaje) {
		IdViaje = idViaje;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCalleOrigen() {
		return calleOrigen;
	}

	public void setCalleOrigen(String calleOrigen) {
		this.calleOrigen = calleOrigen;
	}

	public Integer getNumeroOrigen() {
		return numeroOrigen;
	}

	public void setNumeroOrigen(Integer numeroOrigen) {
		this.numeroOrigen = numeroOrigen;
	}

	public Integer getCPOrigen() {
		return CPOrigen;
	}

	public void setCPOrigen(Integer cPOrigen) {
		CPOrigen = cPOrigen;
	}

	public Date getFechaOrigen() {
		return fechaOrigen;
	}

	public void setFechaOrigen(Date fechaOrigen) {
		this.fechaOrigen = fechaOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getCalleDestino() {
		return calleDestino;
	}

	public void setCalleDestino(String calleDestino) {
		this.calleDestino = calleDestino;
	}

	public Integer getNumeroDestino() {
		return numeroDestino;
	}

	public void setNumeroDestino(Integer numeroDestino) {
		this.numeroDestino = numeroDestino;
	}

	public Integer getCPDestino() {
		return CPDestino;
	}

	public void setCPDestino(Integer cPDestino) {
		CPDestino = cPDestino;
	}

	public Date getFechaDestino() {
		return fechaDestino;
	}

	public void setFechaDestino(Date fechaDestino) {
		this.fechaDestino = fechaDestino;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
