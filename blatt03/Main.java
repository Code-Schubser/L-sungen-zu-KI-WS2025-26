import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Vergleich von MinimaxAI und NaiveMinimaxAI");

        // 1. Beginne Szenario
        GameState startState = new GameState();

        // 2. Teste die naive KI
        System.out.println("--- Teste NaiveMinimaxAI ---");
        NaiveMinimaxAI naiveAI = new NaiveMinimaxAI();
        Point naiverZug = naiveAI.findeBestenZug(startState);
        System.out.println("Naiver bester Zug ist " + naiverZug.x + ", " + naiverZug.y);

        // 3. Teste die Alpha-Beta KI
        System.out.println("--- Teste NaiveMinimaxAI ---");
        MinimaxAI alphaBetaAI = new MinimaxAI();
        Point alphaBetaZug = alphaBetaAI.findeBestenZug(startState);
        System.out.println("Alpha-Beta bester Zug ist " + alphaBetaZug.x + ", " + alphaBetaZug.y);
    }

}
