/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;
import java.io.Serializable;

public class Work implements WorkPrivateInterface, WorkPublicInterface, Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String name;

	private int year;
	
	private User author;
	
	public int value = 0;
	
	public Work( String id, User login, int y, String name)
	{
		this.setId(id);
		this.setYear(y);
		this.setName(name);
		this.setAuthor(login);
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public void setYear(int y) {
		year = y;
	}
	
	@Override
	public int getYear() {
		return year;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setAuthor(User user) {
		this.author = user;
	}
	
	@Override
	public User getAuthor() {
		return author;
	}
	
	@Override
	public void setValue(int price) {
		this.value = price;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
	public String infoWork(){
		String[] array = new String[] {id, name, Integer.toString(year), 
				Integer.toString(value), author.getLogin(), author.getName()};
		
		return String.join(" ",  array);
	}

}
