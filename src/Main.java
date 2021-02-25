import java.io.FileNotFoundException;

public class Main {

    public static void main(String [] args) {
        // Wypelnienie wszystkich liter
        boolean end = false;

        GuessTheMovie guessTheMovie = new GuessTheMovie();

        // Odczytuje tytuly z pliku
        try {
            guessTheMovie.readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found");
        }
        // Losuje tytul i przygotowuje tablice
        guessTheMovie.random();
        // Wyswietla teksty powitalne
        guessTheMovie.guessedTitle();
        // Petla glowna robiona tak dlugo, az wygra

boolean ck = false;
        while (!end)   {
            // Odczytuje znak i sprawdza czy juz zostal podany. Jesli tak wyswietla ponowny monit
           while (!ck) {
                guessTheMovie.inputLetter();
                ck = guessTheMovie.checkOldLetters();
                if (ck) {
                    System.out.println();
                    System.out.println("You have already typed letter " + guessTheMovie.getInput() +".");
                    System.out.println("Type another letter.");
                    ck = false;
                } else {
                    break;
                }

            }

            // Sprawdza, czy wprowadzona litera znajduje sie w tytule
            boolean cL = guessTheMovie.checkLetter();

            if (cL) {
                System.out.println("Correct! There is a letter '" + guessTheMovie.getInput() + "'.");
            } else {
                System.out.println("You have guessed " + guessTheMovie.getWrongLetter() + " wrong letter.");
                System.out.println();
            }

            // Sprawdza czy odgadl caly tytul
            guessTheMovie.odgadlTytul();


            // Sprawdza czy wypelnione sa wszystkie znaki w zalozonej liczbie prob
            if (guessTheMovie.odgadlTytul() && guessTheMovie.getWrongLetter()<10) {
                System.out.println("You win!!!");
                System.out.println("The title is: " + guessTheMovie.getGuessedTitle());
                end = true;
            } else if (!guessTheMovie.odgadlTytul() && guessTheMovie.getWrongLetter()>=10) {
                System.out.println("You lost.");
                System.out.println("The title is: " + guessTheMovie.getGuessedTitle());
                end = true;
            } else {
                guessTheMovie.guessedTitle();
            }
        }
        System.out.println();
        System.out.println("End of the game.");
    }
}
