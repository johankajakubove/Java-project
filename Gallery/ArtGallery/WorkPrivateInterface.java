/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

public interface WorkPrivateInterface {
	
	public void setId(String id);
	
	public String getId();

	public void setYear(int year);
	
	public int getYear();
	
	public void setName(String name);
	
	public String getName();
	
	public void setAuthor(User user);
	
	public User getAuthor();
	
	public void setValue(int value);
	
	public int getValue();
	
	/**
	 * @return String "id name year highestReachedValue authorLogin authorName";
	 * If the highest reached value has not yet been auctioned, the last sale price is zero.
	 */
	public String infoWork();
}
