package io.github.amarcinkowski.solutionframework.browser;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RobotHelper {

	private static Logger logger = LoggerFactory.getLogger(RobotHelper.class);

	public final static int DELAY = 3;

	private static Robot robot;
	static {
		try {
			robot = new Robot();
			robot.setAutoDelay(DELAY);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	private static int getK(char c) {
		try {
			return KeyEvent.class.getField("VK_" + c).getInt(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			return -1;
		}
	}

	public static void press(boolean alt, boolean ctrl, char c) throws InterruptedException {
		int keyCode = getK(c);
		press(alt, ctrl, keyCode);
	}

	private static void press(boolean alt, boolean ctrl, int keyCode) throws InterruptedException {
		logger.trace("KEYPRESS " + (alt ? "ALT+" : "") + (ctrl ? "CTRL+" : "") + keyCode);
		Thread.sleep(DELAY);
		if (alt) {
			robot.keyPress(KeyEvent.VK_ALT);
		}
		if (ctrl) {
			robot.keyPress(KeyEvent.VK_CONTROL);
		}
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
		if (alt) {
			robot.keyRelease(KeyEvent.VK_ALT);
		}
		if (ctrl) {
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		Thread.sleep(DELAY);
	}

	private static void saveAndCloseCombination(String num) {
		try {
			Thread.sleep(1500);
			press(false, true, KeyEvent.VK_S);
			Thread.sleep(500);
			for (char c : num.toCharArray()) {
				press(false, false, RobotHelper.getK(c));
			}
			Thread.sleep(500);
			press(false, false, KeyEvent.VK_ENTER);
			Thread.sleep(1200);
			press(false, true, KeyEvent.VK_W);
			Thread.sleep(1200);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public static String getPageContent() {
		try {
			Thread.sleep(4000);
			press(false, true, KeyEvent.VK_A);
			press(false, true, KeyEvent.VK_C);
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			return c.getData(DataFlavor.stringFlavor).toString();
		} catch (InterruptedException | HeadlessException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void closePage() {
		try {
			Thread.sleep(100);
			press(false, true, KeyEvent.VK_W);
		} catch (InterruptedException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	public static void switchWindowAndSaveAll(String file) {
		try {
			press(true, false, KeyEvent.VK_TAB);
			Thread.sleep(600);

			// go to 1st tab (chrome / firefox compatibile)
			press(true, false, KeyEvent.VK_1);
			saveAndCloseCombination(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getSource() {
		try {
			Thread.sleep(3000);
			press(false, true, KeyEvent.VK_U);
			Thread.sleep(400);
			press(false, true, KeyEvent.VK_A);
			press(false, true, KeyEvent.VK_C);
			closePage();
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			return c.getData(DataFlavor.stringFlavor).toString();
		} catch (InterruptedException | HeadlessException | UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
