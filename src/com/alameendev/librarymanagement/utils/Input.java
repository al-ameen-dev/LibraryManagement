package com.alameendev.librarymanagement.utils;

import java.util.Scanner;

public class Input {

	private static Input input;
	private Scanner scanner;
	
	private Input() {
		scanner = new Scanner(System.in);
	}

	public static synchronized Input in() {
		if(input == null) {
			input = new Input();
		}
		return input;
	}
	
	public Scanner getScanner() {
		return scanner;
	}

}
