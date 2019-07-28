package com.practice.ms.LimitsService.beans;

import org.springframework.beans.factory.annotation.Value;

public class LimitConfiguration {
	
	@Value("${minimum}")
	private int minimum;

	@Value("${maximum}")
	private int maximum;

	public LimitConfiguration() {
		super();
	}
	
	public LimitConfiguration(int minimum, int maximum) {
		super();
		this.minimum = minimum;
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	
}
