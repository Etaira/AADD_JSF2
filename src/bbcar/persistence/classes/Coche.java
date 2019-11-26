package bbcar.persistence.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Coche implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String matricula;
	private String modelo;
	private int anyo;
	private int confort;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Usuario propietario;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "coche")
	private List<Viaje> viajes;
	
	public Coche() {
		super();
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
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public int getConfort() {
		return confort;
	}
	public void setConfort(int confort) {
		this.confort = confort;
	}
	public Usuario getPropietario() {
		return propietario;
	}
	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	
	public void setViaje(Viaje viaje) {
		this.viajes.add(viaje);
	}
	
}
