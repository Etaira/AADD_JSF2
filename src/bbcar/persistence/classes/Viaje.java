package bbcar.persistence.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Table
@Entity
@NamedQuery(name="findViajeById",
query="SELECT model from Viaje model where model.id = :id")
public class Viaje implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Coche coche;
	@Column(name = "num_plazas")
	private Integer numPlazas;
	private Double precio;
	@CollectionTable(name = "notas_viaje")
	@ElementCollection(fetch = FetchType.EAGER)
	private ArrayList<String> notasViaje;
	@OneToMany(cascade = { CascadeType.REMOVE }, mappedBy = "viaje")
	@OrderBy("estado ASC")
	private List<Reserva> reservas;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Parada origen;
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Parada destino;

	public Viaje() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ArrayList<String> getNotasViaje() {
		return notasViaje;
	}

	public void setNotasViaje(ArrayList<String> notasViaje) {
		this.notasViaje = notasViaje;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
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

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
}