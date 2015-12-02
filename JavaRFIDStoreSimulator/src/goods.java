import java.lang.String;

//Commodity in Grocery Store
public class goods {
	public String itemName; //not mandatory needed
	public Integer goodsId;
	public Double percentage;
	private sellingArea inArea; 
	
	
	public goods(Integer Id, Double percent,sellingArea inarea ) 
	{
		//this.setItemName(itemName);
		this.goodsId = Id;
		this.percentage = percent;
		this.setInArea(inarea);
	}

	
	//setter and getter:
	
	/**
	 * @return the inArea
	 */
	public area getInArea() {
		return inArea;
	}


	/**
	 * @param inArea the inArea to set
	 */
	public void setInArea(sellingArea inarea) {
		this.inArea = inarea;
		inarea.addGoods(this);
	}
	public String toString(){
		return this.goodsId.toString();
	}

}
