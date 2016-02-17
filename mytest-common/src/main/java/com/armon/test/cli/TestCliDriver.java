package com.armon.test.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class TestCliDriver {
	
	public Options createOptions() {
		Options options = new Options();
	    options.addOption("h", "help", false, "list all options");
	    options.addOption("q", "quit", false, "quit");
	    return options;
    }
	
	public void printHelp(Options options) {
	    HelpFormatter formatter = new HelpFormatter();
	    formatter.printHelp(" ", options);
	}

	public static void main(String[] args) throws ParseException {
		TestCliDriver cliDriver = new TestCliDriver();
	    Options options = cliDriver.createOptions();
	    if (args.length < 1) {
	      cliDriver.printHelp(options);
	      System.exit(-1);
	    }

	    CommandLineParser parser = new DefaultParser();
	    CommandLine cmd = parser.parse(options, args);
	    
	    if (cmd.hasOption("h")) {
	    	cliDriver.printHelp(options);
	    } else if (cmd.hasOption("q")) {
	        System.out.println("quit...");
	    	System.exit(-1);
	    } else {
	    	System.err.println("wrong option");
	    }
	}

}

