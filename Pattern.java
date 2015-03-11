import java.io.Serializable;


/**
 * Class that store user's pattern
 * @author LeafCherngchaosil
 *
 */
public class Pattern implements Serializable {
	
	/**
	 * The pattern stored as string
	 */
	String mPattern;
	
	
	public Pattern(String pPattern)
	{
		mPattern = pPattern;
	}
	
	/**
	 * Return the user's pattern
	 * @return The user's pattern in string
	 */
	public String getPattern()
	{
		return mPattern;
	}
	
	/**
	 * Override the hashCode function from Object
	 */
	public int hashCode()
	{
		String aString = mPattern;
		return aString.hashCode();
	}
	
	/**
	 * Override the equals function from Object class
	 * @return True if equals, false otherwise
	 */
	public boolean equals(Object pObject)
	{
		if(pObject == this)
			return true;
		else if( !(pObject instanceof Pattern))
			return false;
		Pattern thePattern = (Pattern) pObject;
		if( mPattern.equals(thePattern.getPattern()))
			return true;
		else
			return false;
	}
}
