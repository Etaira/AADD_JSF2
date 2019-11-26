package bbcar.persistence.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String usuario;
	private String clave;
	private String email;
	private String profesion;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
	private String nombre;
	private String apellidos;
	@OneToOne(cascade = { CascadeType.REMOVE },optional = true)
	private Coche coche;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "usuario")
	private List<Reserva> reservas;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "usuarioReceptor")
	private List<Valoracion> valoracionesRecibidas;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "usuarioEmisor")
	private List<Valoracion> valoracionesEmitidas;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Valoracion> getValoracionesRecibidas() {
		return valoracionesRecibidas;
	}

	public void setValoracionesRecibidas(List<Valoracion> valoracionesRecibidas) {
		this.valoracionesRecibidas = valoracionesRecibidas;
	}

	public List<Valoracion> getValoracionesEmitidas() {
		return valoracionesEmitidas;
	}

	public void setValoracionesEmitidas(List<Valoracion> valoracionesEmitidas) {
		this.valoracionesEmitidas = valoracionesEmitidas;
	}

	public boolean claveCorrecta(String password) {
		if(this.clave.equals(password)) {
			return true;
		}else return false;
	}
}
