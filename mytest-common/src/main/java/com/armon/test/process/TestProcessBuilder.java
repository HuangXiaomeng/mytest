package com.armon.test.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class TestProcessBuilder {
	public static void main(String args[]) throws IOException {
		List<String> commands = new ArrayList<String>();
		commands.add("uptime");

		ProcessBuilder pb = new ProcessBuilder();
		pb.command(commands);
		pb.redirectErrorStream(true);
		Process process = pb.start();

		int exitCode = -1;
		try {
			exitCode = process.waitFor();
		} catch (InterruptedException e) {
			System.err.println("Process interrupted. Exit code is " + exitCode);
		}

		StringBuilder result = new StringBuilder();
		final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		try {
	       String line;
	       while ((line = reader.readLine()) != null) {
	    	   result.append(line);
	       }
		} catch (IOException e) {
			System.err.println("failed to read output from process");
		} finally {
			IOUtils.closeQuietly(reader);
		}

		System.out.println(result.toString());

	}
}
