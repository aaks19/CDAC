package com.app.core;

public enum Course {
	JAVA(75), PYTHON(70), DBT(80), MERN(85), CSHARP(77);

	private int minScore;

	private Course(int score) {
		this.minScore = score;
	}
	//getter , setter

	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}
	@Override
	public String toString() {
		return name() +" - "+minScore;
	}
	
}
