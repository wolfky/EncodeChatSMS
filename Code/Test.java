import java.util.*;
import java.security.*;
import java.io.*;

public class Test
{
	public static void main(String[] args)
		throws FileNotFoundException
	{
		long s=4505661;
		byte[] r=[B@4b67cf4d;

		Random gen=new Random(s);
		Random gen2=new Random(20);
		SecureRandom rando=new SecureRandom();

		PrintStream fileOut=new PrintStream(new File ("First.txt"));
		PrintStream fileOut2=new PrintStream(new File ("Second.txt"));

		for(int i=1; i<=10; i++)
		{
			fileOut.print(gen.nextInt() + " ");
			fileOut2.print(rando.generateSeed(i) + " ");
		}
	}

}