import java.util.HashMap;


//a space area 
public class RadarNode extends area {
	
	public double ReadSuccess = 0.95;										
	public RadarNode() {
		this.link = new HashMap<area,Double>();
		//System.out.println("node being created");
	}
	/*public RadarNode (area anotherAR){
		this.areaId = anotherAR.areaId;
		this.link = anotherAR.link;
	}*/
}
	