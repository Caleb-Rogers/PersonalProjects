public class PokeCards	
{
private String myName;
private String myType;
private int myHealth;

	public PokeCards()
		{
		myName = "None";
		myType = "None";
		myHealth = 0;
		} // PokeCards

	public PokeCards(String newName, String newType, int newHealth)
		{
		myName = newName;
		myType = newType;
		myHealth = newHealth;
		} // PokeCards
	
	public String getName()
		{
		return myName;
		} // getName
	
	public void setName(String newName)
		{
		myName = newName;
		} // setName
	
	public String getType()
		{
		return myType;
		} // getName
	
	public void setType(String newType)
		{
		myType = newType;
		} // setType
	
	public int getHealth()
		{
		return myHealth;
		} // myHealth
		
	public void setHealth(int newHealth)
		{
		myHealth = newHealth;
		} // setHealth
	
	public String toString()
		{
		String result = "Pokemon's Name: " + myName + "\n";
		result += "Pokemon's Type: " + myType + "\n";
		return result;
		} // toString
	
} // PokeCards