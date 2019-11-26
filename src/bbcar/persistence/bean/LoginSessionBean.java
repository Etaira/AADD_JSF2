package bbcar.persistence.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.Usuario;
import bbcar.persistence.classes.Viaje;
import bbcar.persistence.dao.DAOException;
import controlador.BlaBlaCar;

@ManagedBean
@SessionScoped
public class LoginSessionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String username;
	protected String password;
	protected Usuario usuario;
	private String parameters;
	private Viaje viajeSeleccionado;

	public String getPaginaActual() {
		String p = FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath();
		String app = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
		if (parameters != null) {
			parameters = "?" + parameters;
		} else {
			parameters = "";
		}
		return app + p + parameters;
	}


	public void login() throws DAOException, Exception {
		String pagina = getPaginaActual();
		if (BlaBlaCar.getInstance().login(username, password)) {
			usuario = BlaBlaCar.getInstance().getUsuario(username, password);
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(pagina);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().getExternalContext().redirect("registro.xhtml");
		}
	}

	public void logout() {
		String pagina = getPaginaActual();
		this.usuario = null;
		this.password =  null;
		this.username =  null;
		try {
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isUsuarioLogueado() {
		if (usuario == null)
			return false;
		return true;
	}

	public Coche getCoche() {
		return usuario.getCoche();
	}

	public void setCoche(Coche coche) {
		usuario.setCoche(coche);
	}
	
	public Viaje getViajeSeleccionado() {
		return viajeSeleccionado;
	}

	public void setViajeSeleccionado(Viaje viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
