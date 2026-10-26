import java.util.Scanner;

public class Battleship {

    // costanti sempre in upper case
    static final int COLPI_MAX = 15; // 15 da specifiche
    static final char SIMBOLO_NAVE = 'N';
    static final char SIMBOLO_ACQUA = '~';
    static final char SIMBOLO_COLPITO = 'X';
    static final char SIMBOLO_MANCATO = 'O';

    // TODO: rendere 5 e 3 parametrici (o da configurazione o da termianle con lo Scanner)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int colpi = 0; // Contatore che cresce solo con colpi validi
        int colpite = 0; // Contatore di quante caselle sono state colpite, la nave è affondata se questo vale 3

        System.out.println("BattleShip v 0.1");

        char[][] campo = new char[5][5];
        char[][] navi = new char[5][5];

        // Devo inizializzare il campo da gioco
        for (int r = 0; r < campo.length; r++) {
            for (int c = 0; c < campo.length; c++) {
                campo[r][c] = SIMBOLO_ACQUA;
                navi[r][c] = SIMBOLO_ACQUA;
            }
        }

        // Creo e posiziono la nave da 3 caselle casualmente

        // Come tirare una monetina 50:50, in alternativa pari o dispari
        boolean orizzontale = Math.random() < 0.5;
        if (orizzontale) {
            int riga = (int) (Math.random() * 5); //Non sarà mai 5 perchè arrotondo per difetto, 0-4
            int colonna = (int) (Math.random() * 3);

            // Questo loop è per posizionare la mia nave nella mappa
            // Perché la mia nave, da specifica, è lunga 3
            for (int i = 0; i < 3; i++) {
                navi[riga][colonna + i] = SIMBOLO_NAVE;
            }
        } else {
            int riga = (int) (Math.random() * 3);
            int colonna = (int) (Math.random() * 5);

            for (int i = 0; i < 3; i++) {
                navi[riga + i][colonna] = SIMBOLO_NAVE;
            }
        }

        //stampaCampo(campo);
        //stampaCampo(navi);

        // Continuo a giocare finché non ho colpito la nave 3 volte o non ho superato i 15 colpi
        while(colpite < 3 && colpi < COLPI_MAX){
            // Chiedo le coordinate di attacco
            // TODO: Validare il nunmero anche se metto una stringa (es: "Ciao")
            System.out.println("Inserisci riga (0-4):");
            int riga = scanner.nextInt();
            System.out.println("Inserisci colonna (0-4):");
            int colonna = scanner.nextInt();

            // Valido la coordinata
            if(!coordinataValida(campo, riga, colonna))
                continue;

            // Applico il colpo
            colpi++; // il colpo è valido, quindi lo conto

            if (navi[riga][colonna] == SIMBOLO_NAVE) {
                campo[riga][colonna] = SIMBOLO_COLPITO;
                colpite++;
                System.out.println("Colpito! Colpi rimasti: " + (COLPI_MAX - colpi));
            } else {
                campo[riga][colonna] = SIMBOLO_MANCATO;
                System.out.println("Mancato! Colpi rimasti: " + (COLPI_MAX - colpi));
            }

            System.out.println();
            stampaCampo(campo);
        }

        // Esito finale: vittoria o sconfitta
        if (colpite == 3) {
            System.out.println("Nave affondata in " + colpi + " colpi!");
        } else {
            System.out.println("Hai perso! La nave era qui:");
            stampaCampo(navi);
        }

        scanner.close();
    }

    private static void stampaCampo(char[][] campo) {

        // TODO: Riconvertire con un for-each
        // r sta per riga, c sta per colonna (andava bene anche "i" e "j"
        for (int r = 0; r < campo.length; r++) {
            for (int c = 0; c < campo.length; c++) {
                System.out.print(campo[r][c]);

                if (c < campo[r].length - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // metodo "ausiliario" o di "appoggio" che ha il compito di verificare la coordinata
    private static boolean coordinataValida(char[][] campo, int riga, int colonna){
        // Valido la coordinata
        if(riga < 0 || riga >= campo.length || colonna < 0 || colonna >= campo[0].length){
            System.out.println("Coordinate fuori dal campo di gioco, riprova");
            System.out.println();

            return false;
        }

        // Se giocatore spara due volte nello stesso punto? Non lo considero valido
        if(campo[riga][colonna] != SIMBOLO_ACQUA){
            System.out.println("Hai già sparato qui, riprova");
            System.out.println();

            return false;
        }

        return true;
    }
}