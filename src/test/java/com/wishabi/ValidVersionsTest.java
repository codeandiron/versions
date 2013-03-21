package com.versions;

import java.util.Arrays;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ValidVersionsTest {
	
	private String versionString;
	
    @Parameters
    public static Collection<Object[]> generateData() {
        return Arrays.asList(new Object[][] {
                 {"1.3.0"},
                 {"1.13.a"},
                 {"0.1332.a.54.0.123"},
                 {"509.g.156"},
                 {"2.2.0.2"}
                 });
    }
	
	public ValidVersionsTest(String versionString){
		this.versionString = versionString;
	}
	
	@Test
	public void testBasicVersion(){
		Assert.assertTrue(versionString.matches(VersionSorter.VERSION));
	}
}
