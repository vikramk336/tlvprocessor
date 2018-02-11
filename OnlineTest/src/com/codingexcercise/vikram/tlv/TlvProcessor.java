package com.codingexcercise.vikram.tlv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TlvProcessor {
/*
 * refactor function
 * checks for the input types and parses the input
 */
	public static void refactor(String str){
		String[] afterSplit = null;
		if(str != null) {
			afterSplit = str.split("-");
			String type = afterSplit[0];
			String length = afterSplit[1];
			if (!(afterSplit[2].contains("REPLCE") || afterSplit[2].contains("UPPRCS"))) {
				String value = afterSplit[2];
				Parser tlv = new Parser(type, length, value);
				if (tlv.getType().equalsIgnoreCase("UPPRCS")) {
					UpperCase u = new UpperCase();
					System.out.println(tlv.getType() + "-" + u.isValid(tlv.getValue()));
				} else if (tlv.getType().equalsIgnoreCase("REPLCE")) {
					Replace r = new Replace();
					System.out.println(tlv.getType() + "-" + r.isValid(tlv.getValue()));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		try {
			if (args.length > 0) {
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				String line = null;
				//types of inputs
				String REGEX = "UPPRCS|REPLCE";
				//creates a pattern
				Pattern p = Pattern.compile(REGEX);
				int beg = 0;

				//check the end of the file
				while ((line = br.readLine()) != null) {
					if (!(line.contains("UPPRCS") || line.contains("REPLCE"))) {
						System.out.println("Type Not Valid");
					}
					Matcher m = p.matcher(line);
					beg = 0;
					while (m.find()) {
						if (m.start() > 0) {
							int end = m.start();
							String s = line.substring(beg, end);
							refactor(s);
							String str = new String();
							str = line.replace(line.charAt(end) + "", "\n" + line.charAt(end));
							refactor(str);
							beg = end;
						}

					}
					String normalString = line.substring(beg, line.length());
					refactor(normalString);
				}
			}
			else {
				System.out.println("Please input a file");
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}


