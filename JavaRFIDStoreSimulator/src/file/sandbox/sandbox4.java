package file.sandbox;

import java.util.Random;

public class sandbox4 {
	public static void main(String[] args)
	{
		//Random r = new Random();
		//ERROR!!!**********************************************************
		for(int i=0;i<100;i++)
		{
			Random r = new Random();
			int numGoods = (int) (5+ r.nextGaussian());//ERROR!!!
			System.out.println(numGoods);
		}
		
	}
}
