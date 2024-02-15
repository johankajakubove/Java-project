/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;

public interface AuctionPublicInterface{

	/**
	 * @return String of infoWorks
	 */
	public String listWorks();
	
	/**
	 * @param id
	 * @return WorkInAuction
	 */
	public WorkInAuction getWorkFromId(String id);
	
}
