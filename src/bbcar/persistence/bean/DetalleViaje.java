package bbcar.persistence.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bbcar.persistence.classes.Coche;
import bbcar.persistence.classes.Viaje;
import controlador.BlaBlaCar;

@ManagedBean
@ViewScoped
public class DetalleViaje implements Serializable{

	private static final long serialVersionUID = 1L;
	protected Integer viajeId;
	protected Viaje viaje;
	protected Coche coche;
	private boolean error;
	
	public DetalleViaje() {
		loadParameters();
		detallar(viajeId);
	}
	
	public Viaje getViaje() {
		return viaje;
	}
	
	public Coche getCoche() {
		return coche;
	}
	
	public Integer getIdViaje() {
		return viajeId;
	}
	
	public void setIdViaje(Integer idViaje) {
		this.viajeId = idViaje;
	}
	
	public void loadParameters() {
		viajeId = JsfUtil.getIntegerHttpRequestParameter("viajeId");
		if (viajeId == null) {
			error = true;
			return;
		}
	}

	public void detallar(Integer id) {
		viaje = BlaBlaCar.getInstance().getViaje(id);
		coche = viaje.getCoche();
	}
}
