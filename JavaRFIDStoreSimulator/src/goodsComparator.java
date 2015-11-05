import java.util.Comparator;

//compare goods according to their id
public class goodsComparator implements Comparator<goods>{
	@Override
	 public int compare(goods g1, goods g2) {
        return g1.goodsId - g2.goodsId;
    }
}
