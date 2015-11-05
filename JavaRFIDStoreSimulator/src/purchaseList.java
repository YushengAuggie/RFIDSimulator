import java.util.ArrayList;
import java.util.Random;

public class purchaseList {
	public ArrayList<goods> allGoodsList;
	private int numGoods=10;
	public int avePurGoods;  //average purchasing number for one customer.
	
	public purchaseList() 
	{
		allGoodsList = new ArrayList<goods>();
		this.setNumGoods(10);
		this.avePurGoods = 8;
	}
	
	
	//generate a purchase list based on avePurGoods; 	Normal Distribution
	//return a arraylist contains all the goods need to buy for one customer
	public ArrayList<goods> generatePurList()
	{
		ArrayList<goods> retArray = new ArrayList<goods>();
		Random r = new Random();
		//**********************************************************
		int numGoods = (int) (this.avePurGoods + r.nextGaussian());
		//**********************************************************
		
		Random random = new Random();
		for(int i=0; i<numGoods;i++)
		{
			Random tempR = new Random();
			int index = tempR.nextInt(this.getNumGoods());
			goods curGoods = this.allGoodsList.get(index);
			boolean PassSameAreaItem = false;
			
			for(goods preGoods:retArray){
				if(preGoods.getInArea() == curGoods.getInArea())
					PassSameAreaItem = true;
			}
			//if curGoods in the area has purchased before
			// 95% this costomer won't buy curGoods
			int randomInt = random.nextInt(100);;
			if(PassSameAreaItem==true){
				if(randomInt>95){
					retArray.add(curGoods);
				}
			}
			else{
				retArray.add(curGoods);
			}
		}
		
		return retArray;
	}
	
	/**
	 * @return the numGoods
	 */
	public int getNumGoods() {
		return numGoods;
	}

	/**
	 * @param numGoods the numGoods to set
	 */
	public void setNumGoods(int numGoods) {
		this.numGoods = numGoods;
	}
	
	
	

	
	//add goods in the whole list
	public boolean addGoods(goods gd)
	{
		if(this.allGoodsList.add(gd))
		{
			setNumGoods(getNumGoods() + 1);
			return true;
		}
		
		return false;
	}
	
	//delete goods in the whole list
	public boolean delGoods(goods gd) 
	{
		if(this.allGoodsList.remove(gd))
		{
			setNumGoods(getNumGoods() - 1);
			return true;
		}
		
		return false;
	}

	
	

	

}
