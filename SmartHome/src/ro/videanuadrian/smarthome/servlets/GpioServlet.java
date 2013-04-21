package ro.videanuadrian.smarthome.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import ro.videanuadrian.smarthome.services.GpioServiceImpl;
import ro.videanuadrian.smarthome.services.abs.GpioService;


public class GpioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private GpioService gpioService;
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		
		gpioService = new GpioServiceImpl();
		
		String state = req.getParameter("state");
		
		System.out.println("state ="+state);
		
		if (state.compareTo("on") == 0){
			gpioService.turnOn(RaspiPin.GPIO_01);
		}
		
		if (state.compareTo("off") == 0){
			gpioService.turnOff(RaspiPin.GPIO_01);
		}
		
		resp.sendRedirect("/SmartHome/index.html");
		//RequestDispatcher view = req.getRequestDispatcher("/pages/index.jsp");
		//view.forward(req, resp);
	}
	
	
}
