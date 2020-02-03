package com.ss.rdmslite.main;
import com.ss.rdmslite.UI.MainMenuUI;


public class App {
	public void greetingMessage() {
		System.out.println("Welcome to the SmoothStack Library Management System.\n");
		MainMenuUI mainMenu = new MainMenuUI();
		
		mainMenu.mainMenuUI();
		
	}
	
	public static void main(String[] args) {
	
		new App().greetingMessage();
		
		
	}

}
