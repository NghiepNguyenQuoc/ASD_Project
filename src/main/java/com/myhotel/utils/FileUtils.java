package com.myhotel.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 */
public class FileUtils {
	public static void appendString(String fileName, String str) throws IOException {
		FileWriter fw = new FileWriter(fileName, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		out.println(str);
		out.flush();
		out.close();
	}

	public static void saveToFile(String fileName, String content) throws IOException {
		FileWriter fw = new FileWriter(fileName, false);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		out.println(content);
		out.flush();
		out.close();
	}

	public static String loadFromFile(String fileName) throws IOException {
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		sc.useDelimiter("\\Z");
		return sc.next();
	}
}
