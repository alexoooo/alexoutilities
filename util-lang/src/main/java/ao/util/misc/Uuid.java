package ao.util.misc;

import java.io.Serializable;
import java.util.Random;

import ao.util.math.rand.MersenneTwister;

/**
 * @author aostrovsky
 *
 */
public class Uuid
		implements Serializable
{
	//--------------------------------------------------------------------
	private static final long serialVersionUID = 1L;


	//--------------------------------------------------------------------
	private final static Random rand = new MersenneTwister(
			Double.doubleToLongBits(
					System.nanoTime() * Math.random()));
	
	
	//--------------------------------------------------------------------
	private final long id;
	
	
	//--------------------------------------------------------------------
	public Uuid()
	{
		id = System.nanoTime() ^ rand.nextLong();
	}
	
	
	//--------------------------------------------------------------------
	@Override public String toString()
	{
		return Long.toHexString( id );
	}

	
	//--------------------------------------------------------------------
	@Override
	public int hashCode()
	{
		return 31 + (int) (id ^ (id >>> 32));
	}

	@Override
	public boolean equals(Object obj)
	{
//		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Uuid other = (Uuid) obj;
		return (id == other.id);
	}
}
