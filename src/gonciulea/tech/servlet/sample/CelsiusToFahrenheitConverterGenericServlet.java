package gonciulea.tech.servlet.sample;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CelsiusToFahrenheitConverterGenericServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		String celsiusTemperature = request.getParameter("celsiusTemperature");
		String result = validateAndConvert(celsiusTemperature);
		dispatchResultToClient(request, response, result);
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

	private void dispatchResultToClient(ServletRequest request, ServletResponse response, String result) {
		try {
			RequestDispatcher rd = request.getRequestDispatcher("convert.jsp");		// let the JSP page to handle the UI
			request.setAttribute("result", result);									// include this in the dispatch
			rd.forward(request, response);											// pass along the origin request/response
		} catch (ServletException e) {
			
		} catch (IOException e) {
			
		}
	}
}
