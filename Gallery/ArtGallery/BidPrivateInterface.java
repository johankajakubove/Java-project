/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

public interface BidPrivateInterface {
	
	public int getValue();
	
	public User getBidder();
	
	/**
	 * @param v
	 */
	public void setValue(int v);
	
	/**
	 * @param u
	 */
	public void setBidder(User u);
}
