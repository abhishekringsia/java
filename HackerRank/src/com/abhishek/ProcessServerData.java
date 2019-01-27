package com.abhishek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessServerData {
   
	
	public static void main(String args[]) {
		 List<Integer> li = new ArrayList<>();
	        int i = 10;
	        li.add(10);
	        int j = li.get(0);
	        System.out.println(j);
		ProcessServerData data = new ProcessServerData();
		List<String> output = data.processInputFile("input.txt");
		Map<String,List<Integer>> x = new TreeMap<> ();
		x.get("abc");
		writeDataToFile("output.txt",output);
	}
	
	static void m(Object o) {
        System.out.println("m(o)");
}
static void m(String s) {
        System.out.println("m(s)");
}
static void m(Integer i) {
        System.out.println("m(i)");
}
static <T> void f(T t) {
        m(t) ;
}

	/**
	 * To Process Input File Data
	 * @param fileName of the file
	 * @return return List of output Data
	 */
	public List<String> processInputFile(String fileName) {
		HashMap<String, Server> softwareDetails = new HashMap<>();
		List<String> output = new ArrayList<>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				String[] serverDetails = line.split(",");
				
				Server server = convertToServer(serverDetails);
				String value = compareSoftareDetails(softwareDetails, server);
				if(value != null ){
					output.add(value);	
				}
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return output;
	}
	
	/**
	 * Compare Software Details with Existing software stored in Map
	 * @param softwareDetails Details of server and software
	 * @param server new server details
	 * @return return server name if its outdated
	 */
	private String compareSoftareDetails(HashMap<String, Server> softwareDetails, Server server) {
		Server existingServer = softwareDetails.get(server.getSoftwareName());
		
		if (existingServer == null) {
			softwareDetails.put(server.getSoftwareName(), server);
		} else {
			int x = versionCompare(server.getSoftwareVersion(), existingServer.getSoftwareVersion());
			
			if (x > 0) {
				softwareDetails.put(server.getSoftwareName(), server);
				return existingServer.getServerName();
			} else if (x < 0) {
				return server.getServerName();
			}
		}
		return null;
	}

	/**
	 * Compare version Strings
	 * 
	 * @param str1 version1 String
	 * @param str2 version2 String
	 * @return return integer value based on comparison
	 */
	public static int versionCompare(String str1, String str2) {
		String[] vals1 = str1.split("\\.");
		String[] vals2 = str2.split("\\.");
		int i = 0;
		// set index to first non-equal ordinal or length of shortest version
		// string
		while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
			i++;
		}
		// compare first non-equal ordinal number
		if (i < vals1.length && i < vals2.length) {
			int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
			return Integer.signum(diff);
		}
		// the strings are equal or one string is a substring of the other
		// e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
		return Integer.signum(vals1.length - vals2.length);
	}

	/**
	 * This Function convert Server Details Array to Server Object
	 * 
	 * @param serverDetails array of server Details
	 * @return Server Object
	 */
	private Server convertToServer(String[] serverDetails) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.setServerName(serverDetails[0].trim());
		server.setSoftwareType(serverDetails[1].trim());
		server.setSoftwareName(serverDetails[2].trim());
		server.setSoftwareVersion(serverDetails[3].trim());
		return server;
	}
	
	/**
	 *  Writes data to File
	 * @param fileName of the file
	 * @param data List of data
	 */
	 private static void writeDataToFile(String fileName,List<String> data) {
	        File file = new File(fileName);
	        FileWriter fr = null;
	        try {
	            fr = new FileWriter(file);
	            for (String line : data) {
	            	fr.write(line);
	            	fr.write("\n");
				}
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            //close resources
	            try {
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
