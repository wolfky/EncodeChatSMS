import java.util.*;
import java.io.*;

public class EncodeChat
{
	public static Scanner kbd=new Scanner(System.in);

	public static void main(String[] args)
		throws FileNotFoundException
	{

		int choice=0;

		while(choice!=4)
		{
			menu();
			System.out.print("Enter the number of the action you would like to do: ");
			choice=kbd.nextInt();

			switch(choice)
			{

				case 1:
					encode();
					break;
				case 2:
					decode();
					break;
				case 3:
					keyGen();
					break;
				default:
					break;
			}
		}



	}//end

	public static void menu()
	{
		System.out.println("\n1. Encode\n"
						 + "2. Decode\n"
						 + "3. Generate Key\n"
						 + "4. Exit\n");
	}

	public static int randomRange(int min, int max)
	{
		double rand=Math.random()*(max-min+1)+min;
		return (int) rand;
	}//end

	public static void keyGen()
		throws FileNotFoundException
	{
	//	String s;
	//	System.out.print("What would you like to name your key file?: ");
	//	s=kbd.next();

		PrintStream keyOut=new PrintStream(new File("key.txt"));

		for(int x=1; x<=10000; x++)
		{
			keyOut.print(randomRange(1000,2000)+" ");
		}//end

		keyOut.close();
		System.out.println("Key created");
	}

	public static void encode()
		throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner data=new Scanner(new File("data.txt"));
		PrintStream encoded=new PrintStream(new File("encoded.txt"));

		while(data.hasNextLine())
		{
			String line=data.nextLine();

			int thisKey;
			//would you like to add a key?
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

		System.out.println("Encoding completed");

	}

	public static void decode()
		throws FileNotFoundException
	{
		Scanner keyIn=new Scanner(new File("Key.txt"));
		Scanner encoded=new Scanner(new File("encoded.txt"));
		PrintStream decoded=new PrintStream(new File("decoded.txt"));
		//was this encoded with an additional key code?
		while(encoded.hasNextInt())
		{
			int thisKey=keyIn.nextInt();
			decoded.print((char)(encoded.nextInt()-thisKey));

		}//end

		System.out.println("Decoding completed");
	}

}//end