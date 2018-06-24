package com.qait.tatoctest;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qait.tatocmain.TatocMain;

public class TatocTest extends TatocMain {

	TatocMain tat = new TatocMain();

	@BeforeClass
	public void testOpenBrowserAndChooseBasic() {
		tat.openUrl();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("grid/gate"));
	}

	@Test
	public void testGreenBox() {
		tat.clickGreenBox();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("frame/dungeon"));
	}

	@Test(dependsOnMethods = "testGreenBox")
	public void testFrameDungeon() {
		tat.frameDungeon();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("basic/drag"));
	}

	@Test(dependsOnMethods = "testFrameDungeon")
	public void testDragAndDrop() {
		tat.dragAndDrop();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("basic/windows"));
	}

	@Test(dependsOnMethods = "testDragAndDrop")
	public void testPopUpWindow() {
		tat.popUpWindow();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("basic/cookie"));
	}

	@Test(dependsOnMethods = "testPopUpWindow")
	public void testCookie() {
		tat.cookie();
		Assert.assertTrue(tat.driver.getCurrentUrl().contains("tatoc/end"));
	}

	@AfterClass
	public void testQuit() {
		driver.quit();
	}

}
