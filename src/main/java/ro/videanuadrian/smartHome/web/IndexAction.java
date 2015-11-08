package ro.videanuadrian.smartHome.web;


import java.util.List;

import ro.videanuadrian.smartHome.entities.TemperatureLog;
import ro.videanuadrian.smartHome.services.TemperatureService;
import ro.videanuadrian.smartHome.services.listeners.impl.InitService;

import ro.videanuadrian.smartHome.web.interceptors.AppInterceptorStack;
import jodd.madvoc.meta.Action;
import jodd.madvoc.meta.InterceptedBy;
import jodd.madvoc.meta.MadvocAction;
import jodd.madvoc.meta.Out;
import jodd.petite.meta.PetiteInject;


@MadvocAction("index")
@InterceptedBy(AppInterceptorStack.class)
public class IndexAction {

	@PetiteInject
	private InitService initService;
	
	@PetiteInject
	private TemperatureService temperatureService;
	
	@Out
	public String temp;
	
	@Action
	public void view() {
		
		List<TemperatureLog> temps = temperatureService.getLastHoutTemperatures();
		
		System.out.println(temps);
		/*
		tempDao = new TemperatureDaoImpl();		
		// display temperatures from last hour
		List<Temperature> temps = tempDao.getLastHourTemperatures();
		
		req.setAttribute("temps", temps);
		RequestDispatcher view = req.getRequestDispatcher("/pages/index.jsp");
		view.forward(req, resp);
		*/
	}
	
	/*
	public void gpio() {
		
		gpioService = new GpioServiceImpl();
		
		String state = req.getParameter("state");
		
		if (state.compareTo("on") == 0){
			gpioService.turnOn(RaspiPin.GPIO_01);
		}
		
		if (state.compareTo("off") == 0){
			gpioService.turnOff(RaspiPin.GPIO_01);
		}
		
		resp.sendRedirect("/SmartHome/index.html");		
	}
	*/
}
