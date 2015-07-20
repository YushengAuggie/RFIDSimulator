
import java.util.HashMap;
import java.util.HashSet;

//Commodity area have some goods
public class sellingArea extends area {
	public HashSet<goods> hasGoods = new HashSet<goods>(); 
	
	public sellingArea() {
		this.link = new HashMap<area,Double>();
	}

	public boolean addGoods(goods gd) {
		return this.hasGoods.add(gd);
	}
	

	public boolean delGoods(goods gd) {
		return this.hasGoods.remove(gd);
	}
	
	
}
