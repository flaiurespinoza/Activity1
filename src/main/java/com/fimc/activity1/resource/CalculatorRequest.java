package com.fimc.activity1.resource;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CalculatorRequest implements Serializable {
	private String operator;
	private Float number1;
	private Float number2;
	
}
