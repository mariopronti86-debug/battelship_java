public class Battleship {

    // costanti sempre in upper case
    static final char SIMBOLO_ACQUA = '~';
    static final char SIMBOLO_NAVE = 'N';
    static final char SIMBOLO_COLPITO = 'X';
    static final char SIMBOLO_MANCATO = 'O';


    // TODO: rendere 5 e 3 parametrici (o da configurazione o da termianle con lo Scanner)
    public static void main(String[] args) {
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

        stampaCampo(campo);
        stampaCampo(navi);
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
}