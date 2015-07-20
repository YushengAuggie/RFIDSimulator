import java.util.HashMap;


public abstract class area {
	
	public HashMap<area,Double> link; //Double is reserved for future use---possibility
	public String areaId;
	
	public void addLink(area node,double possibility)//add link between 2 nodes
	{
		this.link.put(node, possibility);
		node.link.put(this, possibility);
	}

}
