public class GeneticAlgorithmQueenTest {
    public static void main (String[] args){
        float pCross = 0.6f;
        float pMut = 0.20f;
        int tunierGroesse = 100;
        int maxGen = 200;
        int popSize = 100;


        int loops = 100;
        int countSolutions = 0;
        int totalGenerationsForSolutions = 0;


        System.out.println("Starte " + loops + " durchläufe...");
        for (int d = 0 ; d < loops; d++){
            // Testmaschinen
            GeneticAlgorithmQueens ga = new GeneticAlgorithmQueens(
                    pCross, pMut, tunierGroesse, maxGen, popSize
            );

            int generationOfSolution = ga.run();
            if (generationOfSolution  != -1) {
                countSolutions += 1;
                totalGenerationsForSolutions += generationOfSolution;
            }
        }
        // Gesamtergebnis
        System.out.println("--- Auswertung ---");
        System.out.println("Erfolgreiche Läufe: " + countSolutions + " von " + loops);

        // Erfolgsrate
        float sucessRate = (float)countSolutions / loops;
        System.out.println("Erfolgsrate: " + sucessRate * 100 + "%");

        // AES
        if (countSolutions > 0){
            float aes = (float)totalGenerationsForSolutions / countSolutions;
            System.out.println("Average Generation to Solution (AES): " + aes);
        } else {
            System.out.println("AES: Nicht berechenbar da keine Lösung");
        }

    }
}
