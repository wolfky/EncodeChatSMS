import java.io.*;//io
import java.util.*;//scanner

public class Decode
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner encoded=new Scanner(new File("encoded.txt"));
		PrintStream decoded=new PrintStream(new File("decoded.txt"));
		
		while(encoded.hasNextInt())
		{			
			int thisKey=keyIn.nextInt();
			decoded.print((char)(encoded.nextInt()-thisKey));
			
		}//end
		
	}//end
	
}//end