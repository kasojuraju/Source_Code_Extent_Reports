package com.extentreports.practice;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.extentreports.practice.TestBase;

public class TestScript extends TestBase {

	@Test
	public void test() {
		System.out.println("this is test");
		Assert.assertTrue(false, "this is test");
	}

	@Test
	public void test1() {
		System.out.println("this is test1");
		Assert.assertTrue(true, "this is test1");
	}
}
