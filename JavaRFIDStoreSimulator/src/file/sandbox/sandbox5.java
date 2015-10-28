package file.sandbox;

import java.util.ArrayList;

public class sandbox5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for(int i = 0; i<10;i++){
			tempList.add(i);
		}
		System.out.println(tempList);
		ArrayList<Integer> retList = new ArrayList<Integer>();
		for(int i = tempList.size()-1; i >=0 ;i--){
			retList.add(tempList.get(i));
		}
		System.out.println(retList);
	}

}
