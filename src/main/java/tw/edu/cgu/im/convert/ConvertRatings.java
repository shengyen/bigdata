package tw.edu.cgu.im.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConvertRatings {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(
				new FileReader("data/movies.dat"));
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("data/movies.csv"));
		String line;
		while((line=br.readLine())!=null){
			String[] values=line.split("::",-1);
			bw.write(values[0]+","+values[1]+","+values[2]+"\n");
		}	
	}
}
