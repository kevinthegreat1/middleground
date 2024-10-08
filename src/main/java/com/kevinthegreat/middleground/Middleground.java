package com.kevinthegreat.middleground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class Middleground implements ModInitializer {
	public static final String MOD_ID = "middleground";
	public static final String MOD_NAME = "Middleground";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Random RAND = new Random();

	@Override
	public void onInitialize() {
		LOGGER.info(MOD_NAME + " initialized.");
	}

	public static int randX(int max) {
		return RAND.nextInt(Math.max(max - 150, 0));
	}

	public static int randX(int max, int width) {
		return RAND.nextInt(Math.max(max - width, 0));
	}

	public static int randY(int max) {
		return RAND.nextInt(Math.max(max - 20, 0));
	}

	public static int randWidth() {
		return RAND.nextInt(20, 300);
	}

	public static int randColor() {
		return RAND.nextInt(0xFFFFFF);
	}

	public static void shuffle(Object[] t) {
		List<Object> list = new ArrayList<>(List.of(t));
		Collections.shuffle(list, RAND);
		Object[] newList = list.toArray();
		System.arraycopy(newList, 0, t, 0, newList.length);
	}
}
