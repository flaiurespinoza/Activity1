package com.fimc.activity1.resource;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
@Path("/calculator")
public class CalculatorResource implements Serializable{

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response hello(CalculatorRequest calculatorRequest) {
		CalculatorResponse calculatorResponse = new CalculatorResponse();
		if(StringUtils.isEmpty(calculatorRequest.getOperator()) || StringUtils.isEmpty(calculatorRequest.getNumber1()) || StringUtils.isEmpty(calculatorRequest.getNumber2())) {
			return Response.status(Response.Status.BAD_REQUEST)
		              .entity("All fields are required").type( MediaType.TEXT_PLAIN).build();
		}else { 
			String operation = ""; Float x = null;
			if (calculatorRequest.getOperator().equals("+")) {
				operation = "addition";
				x = calculatorRequest.getNumber1() + calculatorRequest.getNumber2();
 			}else if (calculatorRequest.getOperator().equals("-")) {
 				operation = "subtraction";
 				x = calculatorRequest.getNumber1() - calculatorRequest.getNumber2();
 			}else if (calculatorRequest.getOperator().equals("*")) {
 				operation = "multiplication";
 				x = calculatorRequest.getNumber1() * calculatorRequest.getNumber2();
 			}else if (calculatorRequest.getOperator().equals("/")) {
 				operation = "division";
 				
 				if (calculatorRequest.getNumber2() == 0) {
 					return Response.status(Response.Status.BAD_REQUEST)
 	 			              .entity("Invalid Number").type( MediaType.TEXT_PLAIN).build();
 				}else {
 				x = calculatorRequest.getNumber1() / calculatorRequest.getNumber2();
 				}
 			}else { 
 				return Response.status(Response.Status.BAD_REQUEST)
 			              .entity("Invalid operator").type( MediaType.TEXT_PLAIN).build();
 			}
			
			BigDecimal finalResult = new BigDecimal(String.format("%.5f", x));
			
			calculatorResponse.setAction(String.format("%s", operation));
			calculatorResponse.setResult(Double.parseDouble("" + finalResult));
			return Response.ok().entity(calculatorResponse).build();
		}
	}
}
