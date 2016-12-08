import java.io.*;//io
import java.util.*;//scanner

public class Decode
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		Scanner kbd=new Scanner(System.in);

		System.out.print("What is the password? " );
		String x=kbd.nextLine();

		if(x.contains("skcid"))
		{

		System.out.println("Decoding...");

		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner encoded=new Scanner(new File("Encode.txt"));
		PrintStream decoded=new PrintStream(new File("Encode.java"));

		while(encoded.hasNextInt())
		{
			int thisKey=keyIn.nextInt();
			decoded.print((char)(encoded.nextInt()-thisKey));

		}//end

		keyIn.close();
		encoded.close();

		keyIn=new Scanner(new File("Key.txt"));
		Scanner encoded2=new Scanner(new File("Decode.txt"));
		PrintStream decoded2=new PrintStream(new File("Decode.java"));

		while(encoded2.hasNextInt())
		{
			int thisKey=keyIn.nextInt();
			decoded2.print((char)(encoded2.nextInt()-thisKey));

		}//end

		encoded2.close();

		File d=new File("Decode.txt");
		File e=new File("Encode.txt");

		e.delete();
		d.delete();

	}//end

	}//end

}//end
