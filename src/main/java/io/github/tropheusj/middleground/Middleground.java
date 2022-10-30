package io.github.tropheusj.middleground;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.fabricmc.api.ModInitializer;

public class Middleground implements ModInitializer {
	public static final Random RAND = new Random();

	@Override
	public void onInitialize() {
		System.out.println("Middleground initialized.");
	}

	public static int rand(int max) {
		return RAND.nextInt(max);
	}

	public static int randWidth() {
		return RAND.nextInt(300);
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
