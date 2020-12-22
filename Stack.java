public class Stack	
{
private static final int MAXSIZE = 60; // validate?????????
private PokeCards[] myList;
private int myTop;

	public Stack()
		{
		int i = 0;
		myList = new PokeCards[MAXSIZE];
		for(i = 0; i < MAXSIZE; i++)
			myList[i] = null;
		myTop = 0;
		} // Stack

	public boolean push(PokeCards thePoke)
		{
		boolean success = false;
		if(!isFull())
			{
			success = true;
			myTop++;
			myList[myTop] = thePoke;
			} // if
		return success;
		} // push
	
	public PokeCards pop()
		{
		PokeCards ans = null;
		if(!isEmpty())
			{
			ans = myList[myTop];
			myTop--;
			} // if
		return ans;
		} // pop

	public boolean isEmpty()
		{
		return (myTop == 0);
		} // isEmpty
	
	public boolean isFull()
		{
		return (myTop == MAXSIZE);
		} // isFull
	
} // Stack