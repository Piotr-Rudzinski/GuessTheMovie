import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class GuessTheMovie {

    // Tablica tytulow
    private String [] tablica = new String[25];
// Losowy numer tytulu
    private int los;
// Wylosowany tytul
    String guessedTitle;
// Dlugosc tytulu
    private int titleLength;
// Liczba zlych liter
    private int wrongLetter = 0;
// Skaner odczytu znaków z klawiatury
    private Scanner scanner = new Scanner(System.in);
// Odczytany znak
    private char input;
// Tablica znakow tytulu
    private char [] charArray;
// Tablica znakow tytulu - pamiec
    private char [] charArrayMemory;
// Wskaznik trafienia litery
    private boolean noChar;
// Tabela podanych liter
    private char[] typedChars = new char[30];
// Wskaznik podania wczesniej danej litery
    private boolean  wasTyped = false;

    private String str;




    GuessTheMovie() {
        typedChars[0] = 2;
        typedChars[1] = 5;
    }

    /**
     *  Metoda odczytuje plik z lista tytulow.
     *
     *  @param
     *  @return
     */
    public void readFile() throws FileNotFoundException {
        File file = new File("movies.txt");
        Scanner fileScanner = new Scanner(file);

        int r = 0;
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            tablica[r] = line;
            r++;
        }
    }

    /**
     *  Metoda losuje tytul. Najpierw wybiera liczbe losowa.
     *
     *  @param
     * @return
     */
    public void random() {
        los = (int)((Math.random() *100)- 1) / 5 ;
        guessedTitle = tablica[los];
        // Zapisuje wylosowany tutul do tablicy jako znaki i tworzy identyczna tablice pomocnicza
        charArray = guessedTitle.toCharArray();
        charArrayMemory = guessedTitle.toCharArray();
        // Wypelnia tablice pomocnicza znakami '_' i uzupelnia ja o spacje we wlasciwych miejscach
        for (int i=0; i<charArrayMemory.length; i++) {
            charArrayMemory[i] = '_';
            if (charArray[i]==' ') {
                charArrayMemory[i] = ' ';
            }
        }
    }


    /**
     *  Metoda wyswietla teksty poczatkowe
     */
    public void guessedTitle() {
        System.out.println();
        System.out.print("You are guessing: ");
        // Wyswietla tytul z tabeli pomocniczej
        for (int i=0; i<charArrayMemory.length; i++) {
            System.out.print(charArrayMemory[i]);
        }
        System.out.println("");
    }


    /**
     *  Metoda odczytuje znak z kalwiatury
     */
    public void inputLetter() {
        System.out.print("Guess a letter: ");
        input = scanner.next().charAt(0);

    }

    /**
     *  Metoda sprawdza czy znak juz wczesniej zostal podany. Jesli nie to dodaje go do tablicy podanych liter
     *
     *  @return wasTyped
     */
    public boolean checkOldLetters() {
        boolean wasTyped = false;

        // Odczytuj adres pierwszej wolnej komorki i zamien char->int
        char position = typedChars[0];

        int q = (int)position;
        boolean equal = false;
        for(int j=1; j<=q; j++) {
            if (typedChars[j] == input) {
                wasTyped = true;
            }
        }

        if (wasTyped) {
            return wasTyped;
        } else {
        // Odczytuj adres pierwszej wolnej komorki i zamien char->int
            position = typedChars[0];
            //a=Character.getNumericValue(position);
            q = (int)position;
            // Zapisz input dopierwszej wolnej komorki
            typedChars[q] = input;
            // Zwieksz licznik wolnych komorek, zmien na char i zapisz na pierwszej pozycji tabeli
            q++;
            //char c=(char)z;Character.forDigit(a,10);
            typedChars[0] = (char)q;

            return wasTyped = false;
            //break;
        }
    }


    /**
     *  Metoda udostepnia pole wrongLetter
     *
     *  @return wrongLetter
     */
    public int getWrongLetter() {
        return wrongLetter;
    }

    /**
     *  Metoda udostepnia pole input
     *
     *  @return input
     */
    public char getInput() {
        return input;
    }



    /**
    *  Metoda odszukuje wprowadzony znak wsrod liter tytulu
    *
    *  */
    public boolean checkLetter() {
         boolean thereIsAChar = false;
 // Odnajduje litery w tytule. Jesli litere odnajdzie to wpisuje ja do tablicy pomocniczej.
         for (int i=0; i<charArray.length;i++) {
            if (charArray[i]==input) {
                charArrayMemory[i]=input;
                // Jesli odnajdzie itere to ustawi noChar przez co nie bedzie powodu do jego zwiekszenia w nastepnym if.
                thereIsAChar = true;
            }
        }
// Licznik blednych liter
        if (thereIsAChar) {
            return thereIsAChar = true;
        } else {
            wrongLetter++;
            return thereIsAChar = false;
        }
    }


    /**
     *  Metoda prawdza, czy tytul jest kompletny (nie ma znaku _)
     * @return full
     *  */
    public boolean odgadlTytul () {
        // Sprawdza czy wypelnione sa wszystkie znaki w zalozonej liczbie prob
        boolean odgadlTytul = true;
        for (int j = 0; j < charArrayMemory.length; j++) {
            if (charArrayMemory[j] == '_') {
                odgadlTytul = false;
            }
        }
        return odgadlTytul;
    }

    public String getGuessedTitle() {
        return guessedTitle;
    }

}
