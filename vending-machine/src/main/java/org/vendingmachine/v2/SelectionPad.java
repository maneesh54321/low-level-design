package org.vendingmachine.v2;

import java.util.Scanner;

public class SelectionPad {

	public int getSelectionInput() {
		try (Scanner sc = new Scanner(System.in)) {
			return Integer.parseInt(sc.nextLine());
		}
	}
}
