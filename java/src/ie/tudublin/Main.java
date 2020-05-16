package ie.tudublin;

import c18487682.cubeWave;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new cubeWave());		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}