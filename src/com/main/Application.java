package com.main;

public class Application {
	private Model model;
	
	public Application() {
		this.model = new Model();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
