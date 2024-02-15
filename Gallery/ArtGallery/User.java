/**
* @author JOHANKA JAKUBOVE (68869) j.jakubove@campus.fct.unl.pt
* @author PETER DUŠKA (68935) p.duska@campus.fct.unl.pt
*/

package ArtGallery;
import java.io.Serializable;

public class User implements UserPublicInterface, Serializable{

	private static final long serialVersionUID = 1L;
	public String name;
	public String login;
	String artName;
	int age;
	String email;
	public boolean artist;

	
	public User( String _login, String _name, String _artName, int _age, String _email, boolean _Artist)
	{
		login = _login;
		name = _name;
		artName = _artName;
		age = _age;
		email = _email;
		artist = _Artist;	
	}
	
	public String getInfoUser()
	{
			return login + " " + name + " " + age + " " + email;
	}
	
	public String getInfoArtist()
	{
		return  login + " " + name + " " + artName + " " + age + " " + email;
	}
	
	public String getLogin()
	{
		return login;
	}
	
	public boolean isArtist()
	{
		return artist;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setGetLogin(String l)
	{
		login = l;
	}
	
	public void setIsArtist(boolean a)
	{
		artist = a;
	}
	
	public void setName(String n)
	{
		name = n;
	}

}
