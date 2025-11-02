import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MinimaxAI {
    // Zähler
    private static long knotenCounter = 0;

    // Initialisierungsmethode
    public Point findeBestenZug(GameState state){
        knotenCounter = 0;

        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        Point besterZug = null;

        // Wenn PLAYER_X beginnt
        if (state.getOnTurn() == GameState.PLAYER_X){
            int besterScore = Integer.MIN_VALUE;

            // Iteriere durch alle Züge und finde den besten
            for (Point zug : state.getMoeglicheZuege()){
                GameState kind = state.erzeugeNeuenZustand(zug);
                int score = min_value(kind, alpha, beta);
                if (score > besterScore){
                    besterScore = score;
                    besterZug = zug;
                    alpha = Math.max(alpha, besterScore);
                }
            }
        }
        // Wenn PLAYER_O beginnt
        if (state.getOnTurn() == GameState.PLAYER_O){
            int besterScore = Integer.MAX_VALUE;

            for (Point zug : state.getMoeglicheZuege()){
                GameState kind = state.erzeugeNeuenZustand(zug);
                int score = max_value(kind, alpha, beta);
                if (score < besterScore){
                    besterScore = score;
                    besterZug = zug;
                    beta = Math.min(beta, besterScore);
                }
            }

        }
        System.out.println("Alpha-Beta-Suche: " + knotenCounter + " Knoten besucht");
        return besterZug;
    }

    // MAX_Funktion
    public int max_value(GameState state, int alpha, int beta){
        knotenCounter++;
        // Prüfen, ob das Spiel zu Ende ist
        if (state.isTerminal()){
            if (state.hatGewonnen(GameState.PLAYER_X)){
                return +1;
            }
            else if(state.hatGewonnen(GameState.PLAYER_O)){
                return -1;
            }
            else {
                return 0;
            }
        }

        int v = Integer.MIN_VALUE;

        // Iteriere durch alle möglichen Züge, führe Rekursionsfunktion aus und führe Betapruning durch
        List<Point> moeglicheZuege = state.getMoeglicheZuege();
        for (Point zug : moeglicheZuege){
            GameState kindZustand = state.erzeugeNeuenZustand(zug);
            v = Math.max(min_value(kindZustand, alpha, beta), v);
            if (v >= beta){
                return v;
            }
            alpha = Math.max(alpha, v);

        }
        return v;
    }

    // MIN_Funktion
    public int min_value(GameState state, int alpha, int beta){
        knotenCounter++;
        // Prüfen, ob das Spiel zu Ende ist
        if (state.isTerminal()){
            if (state.hatGewonnen(GameState.PLAYER_X)){
                return +1;
            }
            else if(state.hatGewonnen(GameState.PLAYER_O)){
                return -1;
            }
            else {
                return 0;
            }
        }

        int v = Integer.MAX_VALUE;

        // Iteriere durch alle möglichen Züge, führe Rekursionsfunktion aus und führe Alphapruning durch
        List<Point> moeglicheZuege = state.getMoeglicheZuege();
        for (Point zug : moeglicheZuege){
            GameState kindZustand = state.erzeugeNeuenZustand(zug);
            v = Math.min(max_value(kindZustand, alpha, beta), v);
            if (v <= alpha){
                return v;
            }
            beta = Math.min(beta, v);
        }
        return v;
    }

}
