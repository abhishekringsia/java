package com.abhishek;

import java.util.List;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ProcessServerDataTest {
	
	 @Test
	 public void testVersionCompare() {
		 int value =  ProcessServerData.versionCompare("1.5.0", "2.3.0");
		 Assert.assertEquals( "Second version is bigger",-1, value);
	 }
	 
	 @Test
	 public void testVersionCompare2() {
		 int value =  ProcessServerData.versionCompare("1.5.5", "1.5");
		 Assert.assertEquals( "First version bigger",1, value);
	 }
	 
	 @Test
	 public void readInputFileTest() {
		 ProcessServerData serverData = new ProcessServerData();
		 List<String> output = serverData.processInputFile("input.txt");
		 Assert.assertEquals("Output Size Comparison",2,output.size());
	 }
	 

}
