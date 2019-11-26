package bbcar.persistence.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Table
@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String comentario;
	@Enumerated(EnumType.STRING)
	private EstadoReserva estado;
	@ManyToOne
	private Viaje viaje;
	@ManyToOne
	private Usuario usuario;
	//Cada reserva guarda sus valoraciones
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "reserva")
	private List<Valoracion> valoraciones;

	public Reserva() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(List<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}
	
}