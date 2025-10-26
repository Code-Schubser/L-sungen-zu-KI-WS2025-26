import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithmQueens {
    // Zufallsgenerator
    private static final Random random = new Random();

    // Konstanten
    private final float  P_CROSS;
    private final float P_MUT;
    private final int TUNIER_GROESSE;

    // Laufzeitvariablen
    private int maxGen;
    private int popSize;

    // Speicher
    private ArrayList<QueenIndividuum> population;

    public GeneticAlgorithmQueens(float P_CROSS, float P_MUT, int TUNIER_GROESSE, int maxGen, int popSize){
        // Zuweisuung der Parameter
        this.P_CROSS = P_CROSS;
        this.P_MUT = P_MUT;
        this.maxGen = maxGen;
        this.popSize = popSize;
        this.TUNIER_GROESSE = TUNIER_GROESSE;

        // Erzeuge leere Liste
        population = new ArrayList<>();

        // Erstellen der zufälligen Startpopulation
        for (int i = 0; i < popSize; i++){
            population.add(new QueenIndividuum());
        }

    }
    // Durchlauf des GAs
    public int run() {
        // Hauptschleife
        for (int generation = 0; generation < maxGen; generation++){

            // 1. SELEKTION:
            // Erzeuge leeren Mating Pool
            ArrayList<QueenIndividuum> matingPool = new ArrayList<>();

            // Fülle MatingPool durch Tuniere
            for (int tunier = 0; tunier < popSize; tunier++) {
                QueenIndividuum gewinner = tunierSelektion();

                // Füge den Sieger zum Mating Pool hinzu
                matingPool.add(gewinner);

            }
            // 2. CROSSOVER
            ArrayList<QueenIndividuum> neuePopulation = new ArrayList<>();
            for (int i = 0; i < popSize/2; i++){

                // Zufällige ELternteile aus dem Mating Pool wählen
                QueenIndividuum elternA = matingPool.get(random.nextInt(popSize));
                QueenIndividuum elternB = matingPool.get(random.nextInt(popSize));

                // Gen Stränge der Kinder vorbereiten
                int[] genKindA = new int[8];
                int[] genKindB = new int[8];

                // Crossover Wahrscheinlichkeit durchführen
                // Wenn ja dann kreuzen
                if (random.nextFloat() < P_CROSS){
                    // Schnittpunkt wählen zwischen 1 - 7
                    int k = random.nextInt(7) + 1;
                    // Schleife die jedes gen (g) durchgeht und entsprechend kreuzt
                    for (int g = 0; g < 8; g++){

                        // vor dem Schnittpunkt
                        if (g < k){
                            genKindA[g] = elternA.gene[g];
                            genKindB[g] = elternB.gene[g];
                        }
                        // nach dem Schnittpunkt
                        else {
                            genKindA[g] = elternB.gene[g];
                            genKindB[g] = elternA.gene[g];
                        }
                    }
                }
                // Wenn nein dann kopieren
                else {
                    genKindA = elternA.gene.clone();
                    genKindB = elternB.gene.clone();
                }
                // Kinder erschaffen und der neuenPopulation hinzufügen
                QueenIndividuum kindA = new QueenIndividuum(genKindA);
                QueenIndividuum kindB = new QueenIndividuum(genKindB);
                neuePopulation.add(kindA);
                neuePopulation.add(kindB);

            }
            // 3. MUTATION
            // Gib jedes gen der Kinder die chance zu mutieren
            for (QueenIndividuum kind : neuePopulation){
                boolean istMutiert = false;
                for (int g = 0; g < 8; g++){
                    // Wenn ja, dann setze ein neues zufälliges gen
                    if (random.nextFloat() < P_MUT){
                        kind.gene[g] = random.nextInt(8);
                        istMutiert = true;
                    }
                }
                // Wenn das kind mutiert, dann berechne die Kosten neu
                if (istMutiert){
                    kind.neuberechneKosten();
                }
                if (kind.kosten == 0){
                    return generation;
                }
            }

            // Neue Population übernehmen
            population = neuePopulation;
        }
        //Wenn die Schleife keine Lösung findet
        return -1;
    }
    // Hilfsmethode um ein Tunier durchzuführen und Gewinner zurückzugeben
    public QueenIndividuum tunierSelektion(){
        // Wähle den ersten Teilnehmer zufällig
        int gewinnerIndex = random.nextInt(popSize);
        QueenIndividuum gewinner = population.get(gewinnerIndex);

        // Schleife der Herausfoderer
        for (int i = 1; i < TUNIER_GROESSE; i++){

            //Wähle Kanidaten
            int kanidatIndex = random.nextInt(popSize);
            QueenIndividuum kanidat = population.get(kanidatIndex);

            // Welcher Kanidat ist besser
            if (kanidat.kosten < gewinner.kosten){
                gewinner = kanidat;
            }
        }
        // Gebe Sieger des Tuniers zurück
        return gewinner;
    }

}
