package gonciulea.tech.servlet.sample;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelsiusToFahrenheitConverterHTTPServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String celsiusTemperature = request.getParameter("celsiusTemperature");
		String result = validateAndConvert(celsiusTemperature);
		dispatchResultToClient(request, response, result);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);	//delegate the request to the doPost method
	}
	
	private String validateAndConvert(String celsiusTemperature) {
		
		String result = "BAD_INPUT";
		
		try {
			float t = Float.parseFloat(celsiusTemperature);
			float f = c2f(t);
			float c = f2c(t);
			
			result = celsiusTemperature + " == " + String.valueOf(c)  + " F: " + String.valueOf(f) + " C.";
		} catch (NumberFormatException e) {
			
		}
		
		return result;
	}
	
	private float f2c(float t) {
		return (5.0f / 9.0f) * (t - 32f);
	}

	private float c2f(float t) {
		return (9.0f / 5.0f * t) + 32f;
	}
	
	private void dispatchResultToClient(HttpServletRequest request, HttpServletResponse response, String result) {
		try {
			HttpSession session = request.getSession();
			session.setAttribute("result", result);
			response.sendRedirect("convert-http-servlet");
		} catch (IOException e) {
			
		}
	}
}
