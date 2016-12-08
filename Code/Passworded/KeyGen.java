import java.io.*;//io

public class KeyGen
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		PrintStream keyOut=new PrintStream(new File("Key.txt"));
		
		for(int x=1; x<=10000; x++)
		{
			keyOut.print(randomRange(1000,2000)+" ");
		}//end
	}//end
	
	public static int randomRange(int min, int max)
	{
		double rand=Math.random()*(max-min+1)+min;
		return (int) rand;
	}//end
}//end