package ro.videanuadrian.smarthome.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.videanuadrian.smarthome.dao.TemperatureDaoImpl;
import ro.videanuadrian.smarthome.dao.abs.TemperatureDao;
import ro.videanuadrian.smarthome.entities.Temperature;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private TemperatureDao tempDao;
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		
		tempDao = new TemperatureDaoImpl();		
		// display temperatures from last hour
		List<Temperature> temps = tempDao.getLastHourTemperatures();
		
		req.setAttribute("temps", temps);
		RequestDispatcher view = req.getRequestDispatcher("/pages/index.jsp");
		view.forward(req, resp);
	}
	
	
}
