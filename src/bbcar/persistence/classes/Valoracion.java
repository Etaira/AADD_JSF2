package bbcar.persistence.classes;

import javax.persistence.*;

@Entity
public class Valoracion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String comentario;
	private Integer puntuacion;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Reserva reserva;
	@JoinColumn(name = "usuario_emisor")
	@ManyToOne
	private Usuario usuarioEmisor;
	@JoinColumn(name = "usuario_receptor")
	@ManyToOne
	private Usuario usuarioReceptor;
	
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
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	public Usuario getUsuarioEmisor() {
		return usuarioEmisor;
	}
	public void setUsuarioEmisor(Usuario usuarioEmisor) {
		this.usuarioEmisor = usuarioEmisor;
	}
	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}
	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}
	
}
