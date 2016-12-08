import java.io.*;//io
import java.util.*;//scanner

public class Encode
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner data=new Scanner(new File("data.txt"));
		PrintStream encoded=new PrintStream(new File("encoded.txt"));
		
		while(data.hasNextLine())
		{
			String line=data.nextLine();
			
			int thisKey;
			
			for(int index=0; index<line.length(); index++)
			{
				thisKey=keyIn.nextInt();
				encoded.print( line.charAt(index)+thisKey+844+ " ");
			}//end
			
			thisKey=keyIn.nextInt();
			encoded.print(13+thisKey+844+ " ");
			
			thisKey=keyIn.nextInt();
			encoded.print(10+thisKey+844+ " ");
		}//end
		
	}//end
	
}//end