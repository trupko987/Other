package domaci;

import java.util.Scanner;

public class MainClass1 {

	public static void main(String[] args) {

		Scanner skener = new Scanner(System.in);

		int ponovo = 1;
		//ova petlja i varijabla ponovo su za ponovljeno igranje, ukoliko korisnik odluci da igra ponovo
		do {	

			char[][] igrica; 
			int red = 3, kolona = 3; //pocetna inicijalizacija
			String takmicar1, takmicar2;
			int pobjednik = 0; //ovo sam koristio za provjeru pobjednika
			int praznoPolje = 0; //ovim sam dodijelio sva prazna polja na pocetku
			int brojPoteza = 0; //moze biti max 9 poteza, pa sam taj uslov koristio u petlji
			int greska = 0; //bilo mi je potrebno za dijelove gdje unos nije dobar

			//unos takmicara. Substring sam koristio da bi bez obzira na unos, ispis uvijek bio sa prvim pocetnim slovom i ostalim malim slovima
			System.out.println("Unesite ime prvog takmicara: ");
			takmicar1 = skener.next();
			takmicar1 = takmicar1.toLowerCase();
			String takmicar11 = takmicar1.substring(0, 1);
			takmicar11 = takmicar11.toUpperCase();
			String takmicar12 = takmicar1.substring(1, takmicar1.length());
			takmicar1 = takmicar11+takmicar12;

			System.out.println("Unesite ime drugog takmicara: ");
			takmicar2 = skener.next();
			takmicar2 = takmicar2.toLowerCase();
			String takmicar21 = takmicar2.substring(0, 1);
			takmicar21 = takmicar21.toUpperCase();
			String takmicar22 = takmicar2.substring(1, takmicar2.length());
			takmicar2 = takmicar21 + takmicar22;

			igrica = new char[red][kolona];

			//ispis prazne seme za x/o
			System.out.println("");
			System.out.println(" "+igrica[0][0]+" | "+igrica[0][1]+" | "+igrica[0][2]+" ");
			System.out.println("---+---+---");
			System.out.println(" "+igrica[1][0]+" | "+igrica[1][1]+" | "+igrica[1][2]+" ");
			System.out.println("---+---+---");
			System.out.println(" "+igrica[2][0]+" | "+igrica[2][1]+" | "+igrica[2][2]+" ");
			System.out.println("");

			while(pobjednik == 0) {

				System.out.println("Na potezu je "+takmicar1+" (X). Unesite poziciju: ");
				System.out.println("Red: ");
				red = skener.nextInt();
				red = red-1; //stavljao sam -1 jer racunam da korisnik ne zna da brojanje u javi ide od 0, pa ce za nultu poziciju ukucati 1
				System.out.println("Kolona: ");
				kolona = skener.nextInt();
				kolona = kolona -1;

				if (red < 0 || red > 2 || kolona < 0 || kolona > 2) {
					System.err.println("Unos je van granica igrice!");
					continue; 
				} else if (igrica[red][kolona] != praznoPolje) {
					System.err.println("Polje je zauzeto!");
					continue;
				} else {			
					igrica[red][kolona] = 'X';
				}

				System.out.println("");
				System.out.println(" "+igrica[0][0]+" | "+igrica[0][1]+" | "+igrica[0][2]+" ");
				System.out.println("---+---+---");
				System.out.println(" "+igrica[1][0]+" | "+igrica[1][1]+" | "+igrica[1][2]+" ");
				System.out.println("---+---+---");
				System.out.println(" "+igrica[2][0]+" | "+igrica[2][1]+" | "+igrica[2][2]+" ");
				System.out.println("");

				if (igrica[0][0] == 'X' && igrica[0][1] == 'X' && igrica[0][2] == 'X') {
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[1][0] == 'X' && igrica[1][1] == 'X' && igrica[1][2] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[2][0] == 'X' && igrica[2][1] == 'X' && igrica[2][2] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[0][0] == 'X' && igrica[1][0] == 'X' && igrica[2][0] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[0][1] == 'X' && igrica[1][1] == 'X' && igrica[2][1] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[0][2] == 'X' && igrica[1][2] == 'X' && igrica[2][2] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[0][0] == 'X' && igrica[1][1] == 'X' && igrica[2][2] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				} else if (igrica[0][2] == 'X' && igrica[1][1] == 'X' && igrica[2][0] == 'X'){
					System.out.println("Pobjednik je "+takmicar1);
					pobjednik = 1;
				}

				if (pobjednik == 1) {
					System.out.println("Igra je Zavrsena!");
					break;
				}

				brojPoteza = brojPoteza + 1;

				if (brojPoteza == 9 && pobjednik == 0) {
					System.out.println("Ishod je nerijesen!");
					break;
				}

				// posto za drugog takmicara nisam mogao koristiti continue, jer bi me vratilo ponovo na sam pocetak (prvi takmicar bi dobio ekstra potez), 
				// napravio sam novu do-while petlju samo za drugog takmicara i tu sam koristio varijablu greska
				do {

					greska = 0; //u svakom novom "krugu" sam "resetovao" gresku

					System.out.println("Na potezu je "+takmicar2+" (O). Unesite poziciju: ");
					System.out.println("Red: ");
					red = skener.nextInt();
					red = red - 1;
					System.out.println("Kolona: ");
					kolona = skener.nextInt();
					kolona = kolona - 1;

					if (red < 0 || red > 2 || kolona < 0 || kolona > 2) {
						System.err.println("Unos je van granica igrice!");
						greska = 1;
					} else if (igrica[red][kolona] != praznoPolje) {
						System.err.println("Polje je zauzeto!");
						greska = 1;
					} else {			
						igrica[red][kolona] = 'O';
					}
				} while (greska == 1);

				System.out.println("");
				System.out.println(" "+igrica[0][0]+" | "+igrica[0][1]+" | "+igrica[0][2]+" ");
				System.out.println("---+---+---");
				System.out.println(" "+igrica[1][0]+" | "+igrica[1][1]+" | "+igrica[1][2]+" ");
				System.out.println("---+---+---");
				System.out.println(" "+igrica[2][0]+" | "+igrica[2][1]+" | "+igrica[2][2]+" ");
				System.out.println("");

				if (igrica[0][0] == 'O' && igrica[0][1] == 'O' && igrica[0][2] == 'O') {
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[1][0] == 'O' && igrica[1][1] == 'O' && igrica[1][2] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[2][0] == 'O' && igrica[2][1] == 'O' && igrica[2][2] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[0][0] == 'O' && igrica[1][0] == 'O' && igrica[2][0] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[0][1] == 'O' && igrica[1][1] == 'O' && igrica[2][1] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[0][2] == 'O' && igrica[1][2] == 'O' && igrica[2][2] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[0][0] == 'O' && igrica[1][1] == 'O' && igrica[2][2] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				} else if (igrica[0][2] == 'O' && igrica[1][1] == 'O' && igrica[2][0] == 'O'){
					System.out.println("Pobjednik je "+takmicar2);
					pobjednik = 1;
				}

				if (pobjednik == 1) {
					System.out.println("Igra je Zavrsena!");
					break;
				}

				brojPoteza = brojPoteza + 1;

			}

			System.out.println("Da li zelite ponovo da igrate? Broj 1 za DA, bilo koji drugi broj za NE");
			ponovo = skener.nextInt();

		} while (ponovo == 1);
		
		skener.close();
	}

}
