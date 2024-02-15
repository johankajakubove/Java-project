/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

public interface UserPublicInterface {

	/**
	 * @return String "login name age email";
	 */
	public String getInfoUser();
	
	/**
	 * @return String "login name artName age email";
	 */
	public String getInfoArtist();
	
	public String getName();
	
	public String getLogin();
	
	public boolean isArtist();
		
}
