package com.versions;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class VersionSorterTest {
	
	private String first;
	private String second;
	private String expected;
	
    @Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                 {"1.1.2", "1.1.3","SECOND"},
                 {"1.1.2", "1.1.543","SECOND"},
                 {"1.1.2", "1.1.2","EQUAL"},
                 {"1.a.2", "1.a.2","EQUAL"},
                 {"1.z.2", "1.f.3","FIRST"},
                 {"1.1.2", "1.1.2.a","SECOND"},
                 {"1.1.2", "1.1.3.b","SECOND"},
                 {"1.1.2", "1.1.0","FIRST"},
                 {"1.1.a", "1.1.f","SECOND"},
                 {"1.1", "1.1.3","SECOND"},
                 {"1.1.1.1", "1.2","SECOND"},
                 {"1.2", "1.1.1.1","FIRST"},
                 {"1.1", "1.1.1","SECOND"},
                 {"1.1.1", "1.1","FIRST"},
                 {"1.a", "1","FIRST"},
                 {"1.a", "1.0","FIRST"},
                 {"a", "b","SECOND"},
                 {"f", "b","FIRST"},
                 {"13", "1","FIRST"},
                 {"1", "1.z","SECOND"}
                 });
    }
    
    public VersionSorterTest(String first, String second, String expected){
    	this.first = first;
    	this.second = second;
    	this.expected = expected;
    }
	
	@Test
	public void testAllVersionComparisons(){
		String result = VersionSorter.sortVersions(first, second);
		Assert.assertEquals(expected, result);
	}
}
