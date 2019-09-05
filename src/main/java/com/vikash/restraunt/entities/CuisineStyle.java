package com.vikash.restraunt.entities;

import java.util.List;

public class CuisineStyle {
	
	private List<String> cstyle;

	public List<String> getCstyle() {
		return cstyle;
	}

	public void setCstyle(List<String> cstyle) {
		this.cstyle = cstyle;
	}

	@Override
	public String toString() {
		return "CuisineStyle [cstyle=" + cstyle + "]";
	}

}
