package com.uniovi.sdi;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Borrar
 */
@WebServlet("/borrarDelCarrito")
public class Borrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Borrar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		if (carrito == null) {
			carrito = new HashMap<String, Integer>();
			request.getSession().setAttribute("carrito", carrito);
		} else {
			String producto = request.getParameter("producto");
			if (producto != null) {
				borrarDeCarrito(carrito, producto);
			}
//					Retornar	la	vista	con	parámetro	"carrito"
			request.setAttribute("paresCarrito", carrito);

		}
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void borrarDeCarrito(HashMap<String, Integer> carrito, String claveProducto) {
		int numeroArticulos = (Integer) carrito.get(claveProducto).intValue();
		if (numeroArticulos == 1)
			carrito.remove(claveProducto);
		else {
			carrito.put(claveProducto, new Integer(numeroArticulos - 1));
		}

	}

}
