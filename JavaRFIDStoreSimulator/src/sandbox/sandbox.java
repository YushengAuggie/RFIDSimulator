package sandbox;

import java.util.Random;

//import java.util.Random;

public class sandbox {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				int i=50;
				Random r = new Random();
				while(i>0)
				{
					
					System.out.println(r.nextGaussian());
					
					i--;
				}
				System.out.println("end");
				System.out.println("end");System.out.println("end");System.out.println("end");
				Double d = r.nextGaussian();
				System.out.println(d);
				for(int i1 =0;i1<100;i1++)
				{
					int index  = r.nextInt(10);
					System.out.println(index);
				}
				
				
			String st = "temp" + 5;	
		 System.out.println(st);
	
		
	}

}
