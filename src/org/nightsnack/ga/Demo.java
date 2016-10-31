package org.nightsnack.ga;

import java.io.IOException;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GA ga = new GA();//96,48,50000,0.8f,0.001f
		ga.inputMatrix();
//		System.exit(0);
//		try {
//			ga.init("/Users/chiyexiao/Documents/data.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ga.solve();	


	}

}
