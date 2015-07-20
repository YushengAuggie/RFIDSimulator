import java.util.ArrayList;

import javax.xml.crypto.KeySelector.Purpose;


//IDEA 1------PLEASE IGNORE---------
	//to generate path:
	//generate a random target node x needed to reach
	//then find randomly choose one path from node 0 to node x
	//then find a path to go back from node x to node 0;

	//if speed considered, speed can be a random number, for example from 0.1 to 1
	
	//False Negative: once the real path sequence is generated, we can deleted lets say 5% records as radar can read fail
	
	//?at last may write result into a file?
	//--------------------------------------
			
			
			
//IDEA II -----currently using:---------
	//Generate items need to buy: ItemList (random number in a range)
	//for(item:itemList)    
	//	List.add(   findPath(currentArea,findArea(item))  ); 
	//                  findPath^--->BFS find the least deep leaf can be reached 
	//										--> Assumption: distance between each node is the same
	//List.add(   findPath(currentArea, Entry) )//entry maybe Radar[0];
	//radar ---> timeReserved ?
	//write numRecord,itemList,Goods-->output.xls 
	//--------------------------------------


public class main {

	public static void main(String[] args) {
		int numRadar = 10;     //10 Radars
		RadarNode[] node = new RadarNode[numRadar]; 
		int numSellingArea = 10;
		sellingArea[] sellAreasNode = new sellingArea[numSellingArea];
		String goodsList = "goodsList.xls";
		
		initialRadarSetting(numRadar, node);
		initialSellingAreaSetting(numSellingArea,sellAreasNode,node);
		purchaseList allGoods =  initialGoodsInitial(goodsList);
		allGoods.avePurGoods = 5;  //can be changed;
		int numCustomer = 10;    //number of buying lists		
		
		for(int i=0;i<numCustomer-1;i++)
		{
			ArrayList curList = allGoods.generatePurList();
			//call find path function ----> ArrayList path = findPath(curList);
			//print and write path --> excelfile "output.xls"
		}
		
		System.out.println("program end");	
		
		
	}
	
	
	//set radar connection
	public static void initialRadarSetting(int numNode, RadarNode[] node)
	{
		
		for(int i =0;i<numNode;i++)
		{
			node[i] = new RadarNode();
			String id = "R"+i;  //Radar
			node[i].areaId = id;
		}
		
		//node 0
		node[0].addLink(node[1], 0.25);		
		node[0].addLink(node[8], 0.25);
		node[0].addLink(node[9], 0.25);
		node[0].addLink(node[7], 0.25);
	
		//node 1
		node[1].addLink(node[8], 0.25);
		node[1].addLink(node[9], 0.25);
		node[1].addLink(node[2], 0.25);
		
		//node 2
		node[2].addLink(node[1], 0.30);
		node[2].addLink(node[3], 0.30);
		
		//node 3
		node[3].addLink(node[4], 0.25);
		node[3].addLink(node[7], 0.25);
		node[3].addLink(node[8], 0.25);
		
		//node 4
		node[4].addLink(node[5], 0.30);
		node[4].addLink(node[7], 0.30);
		
		//node 5
		node[5].addLink(node[6], 0.25);
		node[5].addLink(node[7], 0.25);
		node[5].addLink(node[9], 0.25);
		
		//node 6
		node[6].addLink(node[7], 0.30);
		node[6].addLink(node[9], 0.30);
		
		//node 7
		node[7].addLink(node[8], 0.25);
		node[7].addLink(node[9], 0.25);
		
		//node 8
		node[8].addLink(node[9], 0.25);
		
		//node 9 0,1,7,8 already set

	}
	
	//Setting  selling Area Setting, add selling area and radar link info
	public static void initialSellingAreaSetting(int numSellingArea,sellingArea[] sellAreasNode,RadarNode[] node)
	{
		for(int i =0;i<numSellingArea;i++)
		{
			sellAreasNode[i] = new sellingArea();
			String id = "SA"+i;  //selling area 
			sellAreasNode[i].areaId = id;
			
		}
		
		//selling area 0
		sellAreasNode[0].addLink(node[0], 0.2);
		sellAreasNode[0].addLink(node[1], 0.2);
		sellAreasNode[0].addLink(node[7], 0.2);
		sellAreasNode[0].addLink(node[8], 0.2);
		sellAreasNode[0].addLink(node[9], 0.2);
		
		//selling area 1
		sellAreasNode[1].addLink(node[1], 0.5);
		sellAreasNode[1].addLink(node[2], 0.5);
		
		//selling area 2
		sellAreasNode[2].addLink(node[3], 0.33);
		sellAreasNode[2].addLink(node[2], 0.33);
		sellAreasNode[2].addLink(sellAreasNode[3], 0.33);
		
		//selling area 3
		sellAreasNode[8].addLink(node[3], 0.5);
		//sellAreasNode[3].addLink(sellAreasNode[2], 0.5);
		
		//selling area 4
		sellAreasNode[4].addLink(node[3], 0.33);
		sellAreasNode[4].addLink(node[7], 0.33);
		
		//selling area 5
		sellAreasNode[5].addLink(node[3], 0.5);
		sellAreasNode[5].addLink(node[4], 0.5);
		
		//selling area 6
		sellAreasNode[6].addLink(node[4], 0.5);
		sellAreasNode[6].addLink(node[5], 0.5);
		
		//selling area 7
		sellAreasNode[7].addLink(node[5], 0.33);
		sellAreasNode[7].addLink(node[6], 0.33);
		sellAreasNode[7].addLink(node[7], 0.33);
		
		//selling area 8
		sellAreasNode[8].addLink(node[6], 0.33);
		sellAreasNode[8].addLink(node[9], 0.33);
		sellAreasNode[8].addLink(sellAreasNode[9], 0.33);
		
		//selling area 9
		//sellAreasNode[9].addLink(sellAreasNode[8], 0.33);
		
	}
	
	//add goods list
	//reading data from excel file"goodslist"
	//Goods name(not mandatory needed)||Goods ID||Purchasing Possibility||in selling area  
	public static purchaseList initialGoodsInitial(String goodsList)
	{
		purchaseList retPurList = new purchaseList();
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retPurList;
	}
	
	
}
