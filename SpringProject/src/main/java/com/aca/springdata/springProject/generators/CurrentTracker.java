package com.aca.springdata.springProject.generators;

import java.io.Serializable;

public class CurrentTracker implements Serializable{
	
	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
