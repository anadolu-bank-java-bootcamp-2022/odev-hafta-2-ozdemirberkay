package com.gokhantamkoc.javabootcamp.odevhafta2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayWorldOfMagic implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(PlayWorldOfMagic.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		int maxNumOfAttacksAllowed = 9;
		String[] magicianSpells = createSpellNameRepository();
		float[] spellDamageInfo = createSpellDamageRepository();
		String[] bossNames = createBossNameRepository();
		float[] bossHps = createBossHPRepository();

		int minNumberSpellsUsed = resolveBattle(
				magicianSpells,
				spellDamageInfo,
				bossNames,
				bossHps);

		if (minNumberSpellsUsed > maxNumOfAttacksAllowed) {
			System.out.println("Magician died!");
		} else if (minNumberSpellsUsed > 0 && minNumberSpellsUsed <= maxNumOfAttacksAllowed) {
			System.out.println("Magician won the battle!");
		} else {
			System.out.println("Result is not correct!");
		}
	}

	public static int resolveBattle(
			String[] magicianSpells,
			float[] spellDamageInfo,
			String[] bossNames,
			float[] bossHPs) {

		int spellsUsed = 0;
		// ______ BASLANGIC _______ Kodunuz buradan baslamali

		float[] sortSpells = spellDamageInfo; // büyükten küçüğe doğru sıralanmış büyüler
		for (int index = 0; index < spellDamageInfo.length; index++) {
			for (int sortIndex = 0; sortIndex < index; sortIndex++) {
				if (sortSpells[sortIndex] < sortSpells[sortIndex + 1]) {
					// büyü güçlerini büyükten küçüğe doğru sıralama işini yapan blok
					float temp = sortSpells[sortIndex];
					sortSpells[sortIndex] = sortSpells[sortIndex + 1];
					sortSpells[sortIndex + 1] = temp;
				}
			}
		}
		int counter = 0; // hangi büyünün kullanıldığı bilgisini tutan counter
		for (int bossCounter = 0; bossCounter < bossHPs.length; bossCounter++) {
			while (bossHPs[bossCounter] > 0) {

				// büyüyü kullanır ve bosshpsine etki eder
				// büyü kullanılma değeri burada arttılır

				bossHPs[bossCounter] = bossHPs[bossCounter] - sortSpells[counter % sortSpells.length];
				counter++;
				spellsUsed++;
			}
			counter = 0;
		}

		// ______ SON _______ Kodunuz burada bitmeli
		/*
		 * NOT: ______ BASLANGIC _______ ve ______ SON _______
		 * arasina istediginiz kadar sayida satir ekleyebilirsiniz.
		 */
		return spellsUsed;
	}

	public static String[] createSpellNameRepository() {
		return new String[] { "Ice Storm", "Chain Lightning", "Magic Missile" };
	}

	public static float[] createSpellDamageRepository() {
		return new float[] { 40.0f, 30.0f, 5.0f };
	}

	public static String[] createBossNameRepository() {
		return new String[] { "Dire Rat", "Skeleton Knight", "Undead King" };
	}

	public static float[] createBossHPRepository() {
		return new float[] { 15.0f, 45.0f, 60.0f };
	}
}
