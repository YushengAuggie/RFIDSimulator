import java.util.HashMap;



public class RadarNode {
	public HashMap<RadarNode,Double> link; //Double is reserved for future use---possibility
	public double ReadSuccess = 0.95;										
	public RadarNode() {
		this.link = new HashMap<RadarNode,Double>();
		//System.out.println("node being created");
	}
	
	public void addLink(RadarNode node,double possibility)//add link between 2 nodes
	{
		this.link.put(node, possibility);
		node.link.put(this, possibility);
	}
}
	