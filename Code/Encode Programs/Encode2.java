import java.io.*;//io
import java.util.*;//scanner

public class Encode
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner encode=new Scanner(new File("Encode.java"));
		PrintStream encoded=new PrintStream(new File("Encode.txt"));
		
		while(encode.hasNextLine())
		{
			String line=encode.nextLine();
			
			int thisKey;
			
			for(int index=0; index<line.length(); index++)
			{
				thisKey=keyIn.nextInt();
				encoded.print( line.charAt(index)+thisKey+ " ");
			}//end
			
			thisKey=keyIn.nextInt();
			encoded.print(13+thisKey+" ");
			
			thisKey=keyIn.nextInt();
			encoded.print(10+thisKey+" ");
		}//end
		
		encode.close();
		keyIn.close();
		
		keyIn=new Scanner(new File("Key.txt"));
		Scanner decode=new Scanner(new File("Decode.java"));
		PrintStream decoded=new PrintStream(new File("Decode.txt"));
		
		while(decode.hasNextLine())
		{
			String line=decode.nextLine();
			
			int thisKey;
			
			for(int index=0; index<line.length(); index++)
			{
				thisKey=keyIn.nextInt();
				decoded.print( line.charAt(index)+thisKey+ " ");
			}//end
			
			thisKey=keyIn.nextInt();
			decoded.print(13+thisKey+" ");
			
			thisKey=keyIn.nextInt();
			decoded.print(10+thisKey+" ");
		}//end
		
		decode.close();
		
		File e=new File("Encode.java");
		File d=new File("Decode.java");
		
		d.delete();
		e.delete();
		
	}//end
	
}//end
