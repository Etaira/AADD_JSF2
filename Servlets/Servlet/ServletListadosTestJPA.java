package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbcar.persistence.classes.EntityManagerHelper;
import bbcar.persistence.classes.Parada;
import bbcar.persistence.classes.Viaje;

/**
 * Servlet implementation class ServletListadosTestJPA
 */
@WebServlet("/ServletListadosTestJPA")
public class ServletListadosTestJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletListadosTestJPA() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		EntityManager em = EntityManagerHelper.getEntityManager();
		out.println("<head></head><body>");
		Query q = em.createQuery("SELECT v FROM Viaje v");
		List<Viaje> viajes = q.getResultList();
		out.println("Viajes<br>");
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getNumPlazas() + " : " + viaje.getPrecio() + "<br>");
			Parada parada = viaje.getOrigen();
			out.println(" -> " + parada.getId() + " : " + parada.getCiudad() + "<br>");
		}
		out.println("<br>");
		Integer ultimoIdviaje = 1;
		out.println("Viajes con consulta nativa<br>");
		Query q1 = em.createNativeQuery("SELECT * FROM Viaje", Viaje.class);
		viajes = q1.getResultList();
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getNumPlazas() + " : " + viaje.getPrecio() + "<br>");
		}
		out.println("<br>");
		out.println("Viajes con consulta nombrada<br>");
		Query p1 = em.createNamedQuery("findViajeById").setParameter("id", ultimoIdviaje);
		viajes = p1.getResultList();
		for (Viaje viaje : viajes) {
			out.println(" -> " + viaje.getId() + " : " + viaje.getNumPlazas() + " : " + viaje.getPrecio() + "<br>");
		}
		out.println("<br>");
		out.println("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
