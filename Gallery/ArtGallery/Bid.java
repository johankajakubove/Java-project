/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;
import java.io.Serializable;

public class Bid implements BidPublicInterface, BidPrivateInterface, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int value;
	
	User bidder;
	
	public Bid(int val, User bidder) {
		value = val;
		this.bidder = bidder;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int v) {
		value = v;
	}
	
	public User getBidder() {
		return bidder;
	}
	
	public void setBidder(User u) {
		bidder = u;
	}
}
