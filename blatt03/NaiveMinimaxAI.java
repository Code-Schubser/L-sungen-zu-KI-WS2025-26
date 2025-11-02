import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ich habe NaiveMiniMaxAI für den Vergleich zu MinimaxAI aus meinem Code nochmal generieren lassen
 * da es sich ja quasi um eine Version ohne Pruning handelt
 *
 */
public class NaiveMinimaxAI {

    // Statischer Zähler
    private static long knotenCounter = 0;

    /**
     * Start-Methode (Wrapper) für den naiven Minimax.
     * Findet den besten Zug, ohne Pruning.
     */
    public Point findeBestenZug(GameState state) {
        // Zähler für neuen Suchlauf zurücksetzen
        knotenCounter = 0;

        Point besterZug = null;

        // Fall 1: MAX (PLAYER_X) ist am Zug
        if (state.getOnTurn() == GameState.PLAYER_X) {
            int besterScore = Integer.MIN_VALUE;

            // Iteriere durch alle Züge der ersten Ebene
            for (Point zug : state.getMoeglicheZuege()) {
                GameState kind = state.erzeugeNeuenZustand(zug);
                // Rufe min_value auf (ohne alpha/beta)
                int score = min_value(kind);

                // Wenn dieser Zug besser ist, merke ihn dir
                if (score > besterScore) {
                    besterScore = score;
                    besterZug = zug;
                }
            }
        }
        // Fall 2: MIN (PLAYER_O) ist am Zug
        else if (state.getOnTurn() == GameState.PLAYER_O) {
            int besterScore = Integer.MAX_VALUE;

            // Iteriere durch alle Züge der ersten Ebene
            for (Point zug : state.getMoeglicheZuege()) {
                GameState kind = state.erzeugeNeuenZustand(zug);
                // Rufe max_value auf (ohne alpha/beta)
                int score = max_value(kind);

                // Wenn dieser Zug besser ist (niedrigerer Score), merke ihn dir
                if (score < besterScore) {
                    besterScore = score;
                    besterZug = zug;
                }
            }
        }

        // Zähler-Ausgabe für den Vergleich
        System.out.println("Naive Minimax-Suche: " + knotenCounter + " Knoten besucht.");
        return besterZug;
    }

    /**
     * Die rekursive MAX-Funktion (ohne Pruning)
     */
    public int max_value(GameState state) {
        // Zähle diesen Knotenaufruf
        knotenCounter++;

        // Prüfen, ob das Spiel zu Ende ist
        if (state.isTerminal()) {
            if (state.hatGewonnen(GameState.PLAYER_X)) {
                return +1;
            } else if (state.hatGewonnen(GameState.PLAYER_O)) {
                return -1;
            } else {
                return 0; // Unentschieden
            }
        }

        // Rekursiver Schritt
        int v = Integer.MIN_VALUE;
        List<Point> moeglicheZuege = state.getMoeglicheZuege();

        // Iteriere durch alle möglichen Züge
        for (Point zug : moeglicheZuege) {
            GameState kindZustand = state.erzeugeNeuenZustand(zug);
            // Rufe min_value auf
            v = Math.max(min_value(kindZustand), v);
        }
        return v;
    }

    /**
     * Die rekursive MIN-Funktion (ohne Pruning)
     */
    public int min_value(GameState state) {
        // Zähle diesen Knotenaufruf
        knotenCounter++;

        // Prüfen, ob das Spiel zu Ende ist
        if (state.isTerminal()) {
            if (state.hatGewonnen(GameState.PLAYER_X)) {
                return +1;
            } else if (state.hatGewonnen(GameState.PLAYER_O)) {
                return -1;
            } else {
                return 0; // Unentschieden
            }
        }

        // Rekursiver Schritt
        int v = Integer.MAX_VALUE;
        List<Point> moeglicheZuege = state.getMoeglicheZuege();

        // Iteriere durch alle möglichen Züge
        for (Point zug : moeglicheZuege) {
            GameState kindZustand = state.erzeugeNeuenZustand(zug);
            // Rufe max_value auf
            v = Math.min(max_value(kindZustand), v);
        }
        return v;
    }
}