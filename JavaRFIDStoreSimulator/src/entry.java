import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.xml.crypto.KeySelector.Purpose;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



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


public class entry {

	static int numRadar = 10;     //10 Radars
	static RadarNode[] node = new RadarNode[numRadar]; 
	static int numSellingArea = 10;
	static sellingArea[] sellAreasNode = new sellingArea[numSellingArea];

	public static void main(String[] args) throws IOException {
		
		String goodsList = "../JavaRFIDStoreSimulator/src/file/goodsList.csv";
		
		initialRadarSetting(numRadar, node);
		initialSellingAreaSetting(numSellingArea,sellAreasNode,node);
		purchaseList allGoods =  initialGoodsInitial(goodsList, sellAreasNode);
		
		allGoods.avePurGoods = 5;  //can be changed;
		int numCustomer = 10;    //number of buying lists	
		
		ArrayList<ArrayList<goods>> bigListForAllPurchasingRecords = new ArrayList<ArrayList<goods>>();
		//saving all single customer recordsinto a big array
		
		for(int i=0;i<numCustomer;i++)//this big for loop is for display all the 
										//purchasing behaviours records it has been generated
		{
			ArrayList<goods> curList = allGoods.generatePurList();//single customer puchasing records
			for(goods g: curList){//not mandatory needed
				//System.out.print("itemName: "+g.itemName+" ");
				System.out.print("goodsId: "+g.goodsId+" ");
				System.out.print("percentage: "+g.percentage+" ");
				System.out.println("inArea: "+g.getInArea().areaId+" ");
				//ArrayList<ArrayList<area>> bigPathList = new ArrayList<ArrayList<area>>();
				
			}
			
			//ArrayList<ArrayList<area>> bigPathList = new ArrayList<ArrayList<area>>();
			//call find path function ----> ArrayList path = findPath(curList);
			//print and write path --> excelfile "output.xls"
			//******************************
			ArrayList<area> areaList = goodsListToAreaList(curList);
			
			
			//findPath(bigPathList , curList, new ArrayList<area>(), 0, 1, node[0], curList.get(1).getInArea());
			//System.out.print("bigPathList: "+bigPathList+" ");
			System.out.println();
			bigListForAllPurchasingRecords.add(curList);
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
	//reading data from csv file"goodslist"
	//Goods name(not mandatory needed)||Goods ID||Purchasing Possibility||in selling area  
	public static purchaseList initialGoodsInitial(String goodsList,sellingArea[] sellAreasNode) throws IOException
	{
		purchaseList retPurList = new purchaseList();
		//ArrayList<goods> missingList = new ArrayList<product>();
		//String filepath = strMasterInventory;
		
		Reader in = new FileReader(goodsList);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for (CSVRecord record : records) {
			sellingArea curArea = sellAreasNode[Integer.parseInt(record.get(3))];
			goods curgood = new goods(Integer.parseInt(record.get(1)), Double.parseDouble(record.get(2)), curArea);
			//missingList.add(misProduct);
			retPurList.allGoodsList.add(curgood);
		}
		
		return retPurList;
	}
	
	//find all goods's areas in purchase lists and saves into a areaList(return);
	public static ArrayList<area> goodsListToAreaList(ArrayList<goods> goodsList){
		ArrayList<area> areaList = new ArrayList<area>();
		
		for(goods g: goodsList){
			areaList.add(g.getInArea());
		}

		System.out.println("\n areaList: ");
		for(area a: areaList){
			System.out.print(a.areaId+" ");
		}
		System.out.println();
		return areaList;
	}
	
	
	
	//find path, input a single customer purchasing records
	//find the smallest path for finding all goods
	//change pathList for Rifd records
	public static void findPath(ArrayList<ArrayList<area>> bigPathList, ArrayList<goods> oneCustomerPurchaseList, ArrayList<area> curList, int curGoodNo, int nextGoodNo, area curArea, area nextArea){
	/*
		//System.out.println(curList);
		if(curGoodNo == oneCustomerPurchaseList.size()-1) return;
		//if(curArea==node[0]) return;
		if(curArea == nextArea){
			bigPathList.add(new ArrayList<area>(curList));
			curGoodNo = nextGoodNo;
			nextGoodNo = nextGoodNo + 1;
			//move = true;
			curArea = oneCustomerPurchaseList.get(curGoodNo).getInArea();
			nextArea = oneCustomerPurchaseList.get(nextGoodNo).getInArea();
			findPath(bigPathList, oneCustomerPurchaseList, curList,  curGoodNo, nextGoodNo, curArea, nextArea);
		} 
		
		if(curList.contains(curArea)) return;
		curList.add(curArea);
		for(area linedArea:curArea.link.keySet()){
			findPath(bigPathList, oneCustomerPurchaseList, curList,  curGoodNo, nextGoodNo, linedArea, nextArea);
			curList.remove(curList.size()-1);
		}
	*/
		
		
		//ArrayList<area> curList = new ArrayList<area>();
		
		Queue<area> queue = new LinkedList<area>();
		queue.add(curArea);
		int thisLevel = 1;
		int start;
		while( queue.peek()!=null ){
			start = 0;
			while(thisLevel > 0){
				area tempArea = queue.poll();
				
				if(thisLevel > 0){
					
				}
			}
		}
		
		
		//Here I use Greedy + BFS, 
		//keep search for neighbor area(Radar/SellingArea) 
		//Till reach the next goods' area
		
		
		
		
		
		return;
	}
	

}
