/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

public interface WorkPublicInterface {
	
	public String getId();
	
	public int getYear();
	
	public String getName();
	
	public User getAuthor();
	
	public int getValue();
	
	/**
	 * @return String "id name year highestReachedValue authorLogin authorName";
	 * If the highest reached value has not yet been auctioned, the last sale price is zero.
	 */
	public String infoWork();
}
