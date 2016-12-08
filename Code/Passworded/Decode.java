import java.io.*;//io
import java.util.*;//scanner

public class Decode
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		Scanner kbd=new Scanner(System.in);
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner encoded=new Scanner(new File("encoded.txt"));
		PrintStream decoded=new PrintStream(new File("decoded.txt"));

		System.out.print("Please enter the password: ");
		String key=kbd.nextLine();
		System.out.print("Please enter the key number: ");
		int keyNum=kbd.nextInt();

		if(key.contains("csc414"))
		{
			System.out.println("Decoding... ");

			while(encoded.hasNextInt())
			{
				int thisKey=keyIn.nextInt();
				decoded.print((char)(encoded.nextInt()-thisKey-keyNum));

			}//end
		}//end
	}//end

}//end