import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class GameState {
    // Instanzvariablen
    private char[][] board;
    private char onTurn;

    // Konstanten
    public static final char PLAYER_X = 'x';
    public static final char PLAYER_O = 'o';
    public static final char EMPTY = '-';

    // Konstruktor
    public GameState(){
        this.board = new char[3][3];
        // Alle Felder auf EMPTY setzen
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = EMPTY;
            }
        }
        this.onTurn = PLAYER_X;
    }

    public GameState(GameState orginal){
        this.board = new char[3][3];
        this.onTurn = orginal.onTurn;

        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                this.board[i][j] = orginal.board[i][j];
            }
        }
    }

    // Gibt eine Liste aller freien Felder
    public List<Point> getMoeglicheZuege(){
        ArrayList<Point> moeglicheZuegeListe = new ArrayList<>();
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                if (this.board[i][j] == EMPTY){
                    Point aktuellerPunkt = new Point(i, j);
                    moeglicheZuegeListe.add(aktuellerPunkt);
                }
            }
        }

        return moeglicheZuegeListe;
    }

    // Erzeugt einen neuen Spielzustand anhand eines Zuges
    public GameState erzeugeNeuenZustand(Point zug) {
        GameState kindZustand = new GameState(this);
        kindZustand.board[zug.x][zug.y] = onTurn;
        kindZustand.onTurn = (kindZustand.onTurn == PLAYER_X) ? PLAYER_O : PLAYER_X;
        return kindZustand;
    }

    // Prüft, 8 Gewinnkombinationen für einen Spieler
    public boolean hatGewonnen(char spieler){
        // Prüfe Horizontal
        for(int i = 0; i < this.board.length; i++){
            if (this.board[i][0] == spieler && this.board[i][1] == spieler && this.board[i][2] == spieler){
                return true;
            }
        }

        // Prüfe Vertikal
        for(int j = 0; j < this.board.length; j++){
            if (this.board[0][j] == spieler && this.board[1][j] == spieler && this.board[2][j] == spieler){
                return true;
            }
        }


        // Prüfe Diagonal
        if (this.board[0][0] == spieler && this.board[1][1] == spieler && this.board[2][2] == spieler){
            return true;
        } else if (this.board[2][0] == spieler && this.board[1][1] == spieler && this.board[0][2] == spieler) {
            return true;
        }
        return false;
    }

    // Prüft, ob das Brett voll ist (Unentschieden)
    public boolean istBrettVoll(){
        for (int i = 0; i < this.board.length; i++){
            for (int j = 0; j < this.board[0].length; j++){
                if (this.board[i][j] == EMPTY)
                    return false;
            }
        }
        return true;
    }

    // Prüft, ob das Spiel vorbei ist
    public boolean isTerminal(){
        return hatGewonnen(PLAYER_X) || hatGewonnen(PLAYER_O) || istBrettVoll();
    }

    public char getOnTurn(){
        return this.onTurn;
    }
}
