import java.util.Random;

import static java.lang.Math.abs;


public class QueenIndividuum {
    private static final Random random = new Random();
    public int[] gene;
    public int kosten;

    // Konstruktor (zufällige Gene)
    public QueenIndividuum(){
        gene = new int[8];
        for (int i = 0; i < 8; i++){
            gene[i] = random.nextInt(8);
        }
        kosten = berechneKosten();
    }
    // Konstruktor mit Gen-Array als Parameter
    public QueenIndividuum(int[] gene){
        this.gene = gene;
        this.kosten = berechneKosten();
    }


    // Methode zum berechnen der Zeilen- und Diagonalenkosten
    private int berechneKosten(){
        int totalKosten = 0;
        for (int i = 0; i<8; i++){
            for (int j = i+1; j<8; j++){
                if (gene[i] == gene[j]){
                    totalKosten += 1;
                }
                else if (j-i == abs(gene[j]-gene[i])){
                    totalKosten += 1;
                }
            }
        }

        return totalKosten;
    }

    // Methode zum neuberechnen der Kosten, falls eine Mutation erfolgt
    public void neuberechneKosten(){
        kosten = berechneKosten();
    }
}
