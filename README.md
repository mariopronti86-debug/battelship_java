# 10.70 Esercitazione · Battaglia navale

Lavora sempre sullo stesso file, `BattleShip.java`, e aggiungi una funzionalità alla volta. Alla fine di ogni funzionalità il programma deve compilare e funzionare: provalo prima di passare alla successiva.

Simboli usati nella griglia:

| Simbolo | Significato |
|---|---|
| `~` | acqua non ancora esplorata |
| `N` | nave (il giocatore non la vede) |
| `X` | colpito |
| `O` | mancato |

---

### Funzionalità 1 · Il campo di gioco

Crea `BattleShip.java`. Nel `main` crea un array `char[][] campo` di 5x5 e riempilo di `'~'` con due cicli annidati.

Scrivi un metodo `static void stampaCampo(char[][] campo)` che stampa la griglia, con le celle separate da uno spazio, e chiamalo dal `main`.

```
~ ~ ~ ~ ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
```

**Suggerimento:** il metodo riceve l'array come parametro: usa `campo.length` e `campo[r].length` invece di scrivere 5.

### Funzionalità 2 · Il primo colpo

Crea un secondo array `char[][] navi` di 5x5, pieno di `'~'`, e mettici una nave di 3 celle **in posizione fissa**: riga 2, colonne 1, 2 e 3.

Con lo Scanner chiedi al giocatore riga e colonna di **un solo** colpo:
- se in `navi` c'è `'N'`, stampa `Colpito!` e in `campo` metti `'X'`
- altrimenti stampa `Mancato!` e in `campo` metti `'O'`

Alla fine stampa il campo.

```
Inserisci riga (0-4): 2
Inserisci colonna (0-4): 3
Colpito!

~ ~ ~ ~ ~
~ ~ ~ ~ ~
~ ~ ~ X ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
```

**Suggerimento:** `campo` è quello che vede il giocatore, `navi` è il segreto. Si stampa solo `campo`.

### Funzionalità 3 · I turni

Metti il colpo dentro un ciclo: il gioco continua finché tutte e tre le celle della nave sono colpite. Conta i colpi e, alla fine, stampa:

```
Nave affondata in 7 colpi!
```

**Suggerimento:** servono due contatori: `colpi` (tutti i colpi sparati) e `colpite` (le celle della nave colpite). Il ciclo continua finché `colpite < 3`.

**Prova:** spara tre volte sulla stessa cella della nave. Che cosa succede? E se inserisci la riga 7? Annota i due problemi: li risolvi nella funzionalità 4.

### Funzionalità 4 · Colpi non validi

Prima di usare riga e colonna come indici controlla che il colpo sia valido. Se non lo è, stampa un messaggio e chiedi di nuovo, **senza contare il colpo**:
- coordinate fuori dalla griglia: `Coordinate fuori dalla griglia, riprova`
- cella già colpita: `Hai già sparato qui, riprova`

```
Inserisci riga (0-4): 7
Inserisci colonna (0-4): 1
Coordinate fuori dalla griglia, riprova

Inserisci riga (0-4): 2
Inserisci colonna (0-4): 3
Hai già sparato qui, riprova
```

**Suggerimenti:**
- Il controllo delle coordinate va fatto **prima** di leggere `campo[riga][colonna]`, altrimenti arriva un'`ArrayIndexOutOfBoundsException`
- Una cella è già stata colpita se in `campo` non c'è più `'~'`
- `continue` torna all'inizio del ciclo saltando il resto del turno

### Funzionalità 5 · La nave nascosta

Ora la nave va piazzata **in una posizione casuale**, orizzontale o verticale, sempre lunga 3 celle e sempre tutta dentro la griglia.

**Suggerimenti:**
- `(int) (Math.random() * k)` dà un intero da 0 a `k - 1`
- `Math.random() < 0.5` vale `true` una volta su due: usalo per scegliere la direzione
- Se la nave è orizzontale, la riga va da 0 a 4 ma la colonna di partenza solo da 0 a 2, altrimenti la nave esce dalla griglia. Se è verticale, il contrario
- Per controllare che funzioni, durante le prove stampa anche `navi`, poi togli la stampa

### Funzionalità 6 · Bonus: colpi limitati

Il giocatore ha al massimo **15 colpi**. Se finiscono prima di affondare la nave stampa `Hai perso!` e mostra dove si trovava la nave. Dopo ogni colpo stampa quanti colpi restano.

```
Mancato! Colpi rimasti: 0

Hai perso! La nave era qui:
~ ~ ~ ~ ~
~ N N N ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
~ ~ ~ ~ ~
```

**Suggerimento:** il ciclo ora ha due condizioni: si continua finché la nave non è affondata **e** restano colpi. Dopo il ciclo, un `if` decide se il giocatore ha vinto o perso. `stampaCampo` funziona anche con `navi`.

### Per chi finisce

Idee libere, senza soluzione:
- Due navi, una da 3 e una da 2 celle, che non si sovrappongono
- Il messaggio `Affondata!` quando l'ultima cella di una nave viene colpita
- Numeri di riga e di colonna stampati intorno alla griglia
- Spostare in metodi il piazzamento della nave (`piazzaNave`) e il controllo del colpo (`colpoValido`)