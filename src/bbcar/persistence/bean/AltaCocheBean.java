package bbcar.persistence.bean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.dao.DAOException;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class AltaCocheBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer anyo;
	private Integer nivelConfort;
	private String matricula;
	private String modelo;
	private Integer usuarioId;
	private LoginSessionBean loginSessionBean;

	public AltaCocheBean() {
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		loginSessionBean = (LoginSessionBean) elContext.getELResolver().getValue(elContext, null, "loginSessionBean");
		usuarioId = loginSessionBean.getUsuario().getId();
		Coche c = loginSessionBean.getCoche();
		if (c != null) {
			anyo = c.getAnyo();
			nivelConfort = c.getConfort();
			matricula = c.getMatricula();
			modelo = c.getModelo();
			usuarioId = c.getPropietario().getId();
		}
	}

	public void altaCoche() throws DAOException {
		if (BlaBlaCar.getInstance().findCocheByMatricula(matricula) == null) {
			Coche coche = BlaBlaCar.getInstance().registrarCocheUsuario(matricula, modelo, anyo, nivelConfort,
					usuarioId);
			if (coche != null) {
				loginSessionBean.setCoche(coche);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Coche registrado correctamente", ""));
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problema registrando el coche", ""));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un coche registrado.", ""));
		}
	}

	public void modificarCoche() {
		Coche coche = loginSessionBean.getCoche();
		if (coche != null) {
			coche = BlaBlaCar.getInstance().updateCoche(coche, matricula, modelo, anyo, nivelConfort);
			loginSessionBean.setCoche(coche);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Coche modificado correctamente", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aun no ha registrado un coche", ""));
		}
	}

	public Integer getAnyo() {
		return anyo;
	}

	public void setAnyo(Integer anyo) {
		this.anyo = anyo;
	}

	public Integer getNivelConfort() {
		return nivelConfort;
	}

	public void setNivelConfort(Integer nivelConfort) {
		this.nivelConfort = nivelConfort;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
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
