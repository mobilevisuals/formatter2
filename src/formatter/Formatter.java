package formatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class Formatter {

	public static void main(String[] args) {

		Formatter main = new Formatter();
		ArrayList<String> list = main.readAndSort();
		main.writeAndCount(list);

	}

	private ArrayList<String> readAndSort() {
		
		String textToRead = null;
		File file = new File("src/formatter/kunder.txt");
		ArrayList<String> list = new ArrayList<>();
		try (BufferedReader instream = new BufferedReader(new FileReader(file))) {
			while ((textToRead = instream.readLine()) != null) {
				if (textToRead.length() > 0) {
					StringBuilder stringBuilder = new StringBuilder(textToRead);
					if (stringBuilder.charAt(0) == '0')
						stringBuilder.deleteCharAt(0);
					int position = stringBuilder.indexOf("-");
					if (position != -1)
						stringBuilder.deleteCharAt(position);
					if (stringBuilder.charAt(0) != '+')
						stringBuilder.insert(0, "+46");
					list.add(stringBuilder.toString());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	private void writeAndCount(ArrayList<String> list) {
		Set<String> set = new TreeSet<String>(list);
		try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/formatter/kunder2.txt"));) {

			for (String phoneNumber : set) {
                            //this line takes a lot of time:
				int frequency=Collections.frequency(list, phoneNumber);
                                // if you uncomment the line above and replace it with the line below,
                                //the program will execute at least 10 times faster:
                            //int frequency=2;//test this line instead of the line above
                            //Do you know some way of making this faster? 
                            //It is the counting of how many phone numbers that takes a lot of time.
				if(frequency>1)
				{
				bufferedWriter.write(phoneNumber + ";" + frequency);
				bufferedWriter.newLine();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


