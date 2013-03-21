package com.versions;

public class VersionSorter {
	
	public static final String VERSION = "((([a-z]|([0-9][1-9]+)|[0-9])\\.?)+)(?<!\\.)$";
	
	public static void main(String[] args){
		if((args.length != 2)||(!args[0].matches(VERSION))||(!args[1].matches(VERSION))){
			System.out.println("This method requires two well formatted version strings as command line args");
			System.exit(0);
		}
		
		String result = sortVersions(args[0], args[1]);
		if(result == "FAILED"){
			System.out.println("Failed to perform a comparison between these two version");
		}else if(result == "EQUAL"){
			System.out.println("The two versions are equal");
		}else{
			System.out.println("The " + result + " argument is a newer version");
		}
	}
	
	public static String sortVersions(String version1, String version2){
		//Chop up the versions into items, based on the period separator
		String[] version1Items = version1.split("\\.");
		String[] version2Items = version2.split("\\.");
		
		int maxItems = java.lang.Math.max(version1Items.length, version2Items.length);
		
		for(int index = 0; index<maxItems; index++){
			
			//Figure out if this is the last item of either version string
			boolean lastOfVersion1 = ((index+1) == version1Items.length);
			boolean lastOfVersion2 = ((index+1) == version2Items.length);
			
			if(!(lastOfVersion1 || lastOfVersion2)){
				//Neither string is ending. Look for a difference, if none, go deeper.
				int result = version1Items[index].compareTo(version2Items[index]);
				if(result == 0){
					continue;
				}else if(result > 0){
					return "FIRST";
				}else if(result < 0){
					return "SECOND";
				}
			}else if(lastOfVersion1 && lastOfVersion2){
				//Both strings are ending. Look for a difference, if none, EQUAL.
				int result = version1Items[index].compareTo(version2Items[index]);
				if(result == 0){
					return "EQUAL";
				}else if(result > 0){
					return "FIRST";
				}else if(result < 0){
					return "SECOND";
				}
			}else if(lastOfVersion1){
				//Version1 is ending, but Version 2 is not.
				//Unless 1's item is larger than 2' item, we can assume version 2 is newer
				int result = version1Items[index].compareTo(version2Items[index]);
				if(result <= 0){
					return "SECOND";
				}else if(result > 0){
					return "FIRST";
				}
			}else if(lastOfVersion2){
				//Version2 is ending, but Version 1 is not.
				//Unless 2's item is larger than 1' item, we can assume version 1 is newer
				int result = version2Items[index].compareTo(version1Items[index]);
				if(result <= 0){
					return "FIRST";
				}else if(result > 0){
					return "SECOND";
				}
			}
		}
		return "FAILED";
	}
}