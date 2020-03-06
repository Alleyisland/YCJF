package test;

import java.util.Scanner;

public class TransTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int i=1;
		while (in.hasNextLine()) {
			{
				String s = in.nextLine();
				int index = s.indexOf(",");
				System.out.println(s.substring(0, index-1)+"\t"+s.substring(index+1));
				i++;
			}
		}
	}
}