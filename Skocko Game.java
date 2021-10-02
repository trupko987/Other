package domaci;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ArrayList<Integer> listToCatch = new ArrayList<Integer>();
		ArrayList<Integer> playerList = new ArrayList<Integer>();
		int games = 1;
		int points = 0;

		//prva do-while petlja koja mi broji igre i poene
		do {

			//random sam koristio za odabir slucajne kombinacija. Uzimao sam svaki element posebno, pa sam ih ubacivao u listu
			Random rn = new Random();

			int first  = rn.nextInt(7);
			int secound = rn.nextInt(7);
			int third = rn.nextInt(7);
			int fourth = rn.nextInt(7);

			while (first == 0) {
				first = rn.nextInt(7);
			}
			listToCatch.add(first);

			while (secound == 0) {
				secound = rn.nextInt(7);
			}
			listToCatch.add(secound);

			while (third == 0) {
				third = rn.nextInt(7);
			}
			listToCatch.add(third);

			while (fourth == 0) {
				fourth = rn.nextInt(7);
			}
			listToCatch.add(fourth);

			int attempts = 1;
			int winner = 0;

			do {
				
				if (attempts == 1 && games == 1) {
					System.out.println(attempts+". pokusaj: ");
				} else {
					System.out.println("\n"+attempts+". pokusaj: ");
				}
				
				String player = scanner.next();

				boolean isNumber = player.chars().allMatch(Character::isDigit);

				if (isNumber == false) {
					System.err.println("Mozete da unosite samo brojeve!");
					continue;
				} else if (player.length() != 4) {
					System.err.println("Unos mora imati 4 karaktera!");
					continue;
				} else {
					String num1subs = player.substring(0, 1);
					playerList.add(Integer.parseInt(num1subs));
					String num2subs = player.substring(1, 2);
					playerList.add(Integer.parseInt(num2subs));
					String num3subs = player.substring(2, 3);
					playerList.add(Integer.parseInt(num3subs));
					String num4subs = player.substring(3, 4);
					playerList.add(Integer.parseInt(num4subs));
				}

				boolean checkNumber = true;
				for (int i = 0; i < playerList.size(); i++) {
					if (playerList.get(i) < 1 || playerList.get(i) > 6) {
						System.out.println("Mozete unositi samo brojeve od 1 do 6!");
						checkNumber = false;
					}
				}

				if (checkNumber == false) {
					continue;
				}

				System.out.print(playerList);
				
				int count = 0;
				ArrayList<Integer> tempList = new ArrayList<>(playerList);
				for(int a: listToCatch) {
					if (tempList.contains(a)) {
						tempList.remove(tempList.indexOf(a));
						count++;
					}
				}
				
				//brojanje onih koji su na istim pozicijama, odnosno onih koji su na mjestu
				int correct = 0;
				for (int i=0; i<listToCatch.size(); i++) {

					if (listToCatch.get(i) == playerList.get(i)) {
						correct++;
					}
				}
				
				//provjera i ispis pogodjenih clanova
				if (listToCatch.equals(playerList) && attempts <= 6) {
					winner = 1;
					points = points + 30;
					System.out.println("\nTacna kombinacija! Osvojili ste 30 poena!");
				} else if (listToCatch.equals(playerList) && attempts == 7) {
					winner = 1;
					points = points + 20;
					System.out.println("\nTacna kombinacija! Osvojili ste 20 poena!");
				} else if (listToCatch.equals(playerList) == false && attempts == 7) {
					System.out.println("\nNiste pogodili! \nTacna kombinacija je: "+listToCatch);
				} else {
					if (count == 1) {
						System.out.print(String.format("\t%d pogodjen - %d na mjestu", count, correct));
					} else if (count == 0) {
						System.out.print("\tNijedan pogodjen!");
					} else {
						System.out.print(String.format("\t%d pogodjena - %d na mjestu", count, correct));
					}
				}
				
				//player listu moram obrisati jer ce u drugom pokusaju biti drugi unos
				playerList.clear();
				attempts++;

			} while (attempts <= 7 && winner == 0);

			System.out.println("Da li zelite ponovo da igrate? Y/N");
			
			String newGame = scanner.next();
			char c = newGame.charAt(0);
			char c1 = Character.toLowerCase(c);

			if (c1 == 'y') {
				System.out.println("Izabrali ste novu igru!");
				games = games + 1;
				listToCatch.clear();
			} else if (c1 == 'n') {
				System.out.println(String.format("Zavrsili ste igru! \nBroj odigranih partija: %d \nBroj osvojenih poena: %d", games, points));
				break;
			} else {
				System.err.println("Nepravilan unos!");
			}

		} while (games > 1);

		scanner.close();
	}
}