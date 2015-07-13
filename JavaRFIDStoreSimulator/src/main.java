

public class main {

	public static void main(String[] args) {

		//RadarNode node1 = new RadarNode();
		int numNode = 10;     //10 Radars
		RadarNode[] node = new RadarNode[numNode];  
		
		for(int i =0;i<numNode;i++)
		{
			node[i] = new RadarNode();
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

}
