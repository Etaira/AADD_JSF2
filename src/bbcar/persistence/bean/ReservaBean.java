package bbcar.persistence.bean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Viaje;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class ReservaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Integer viajeId;
	protected Viaje viaje;
	protected String comentario;
	protected Usuario usuario;
	private LoginSessionBean loginSessionBean;
	private Integer usuarioId;
	private boolean error;
	protected Integer reservaId;

	public Integer getViajeId() {
		return viajeId;
	}

	public void setViajeId(Integer viajeId) {
		this.viajeId = viajeId;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LoginSessionBean getLoginSessionBean() {
		return loginSessionBean;
	}

	public void setLoginSessionBean(LoginSessionBean loginSessionBean) {
		this.loginSessionBean = loginSessionBean;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Integer getReservaId() {
		return reservaId;
	}

	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}

	public ReservaBean() {
		loadParameters();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		loginSessionBean = (LoginSessionBean) elContext.getELResolver().getValue(elContext, null, "loginSessionBean");
		usuarioId = loginSessionBean.getUsuario().getId();
	}

	public void loadParameters() {
		viajeId = JsfUtil.getIntegerHttpRequestParameter("viajeId");
		if (viajeId == null) {
			error = true;
			return;
		}
	}

	public void crearReserva() {
		reservaId = BlaBlaCar.getInstance().reservarViaje(viajeId, usuarioId, comentario).getId();
		if (reservaId == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creando la reserva", ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva creada correctamente", ""));
		}
	}

}
