package br.com.aula.joken;
import java.util.Random;

public class Jokenpo {


    private String iaChoice; // Armazena a escolha da IA
    private int playerWins;
    private int iaWins;
    private int consecutiveWins;

    public String playRound(String playerChoice) {
        String[] options = {"pedra", "papel", "tesoura"};
        iaChoice = options[new Random().nextInt(3)]; // Escolha da IA

        if (isPlayerWinner(playerChoice, iaChoice)) {
            playerWins++;
            consecutiveWins++;
            return "player";
        } else if (isIaWinner(playerChoice, iaChoice)) {
            iaWins++;
            consecutiveWins = 0;
            return "ia";
        } else {
            return "draw";
        }
    }

    private boolean isPlayerWinner(String playerChoice, String iaChoice) {
        return (playerChoice.equals("pedra") && iaChoice.equals("tesoura")) ||
                (playerChoice.equals("papel") && iaChoice.equals("pedra")) ||
                (playerChoice.equals("tesoura") && iaChoice.equals("papel"));
    }

    private boolean isIaWinner(String playerChoice, String iaChoice) {
        return (iaChoice.equals("pedra") && playerChoice.equals("tesoura")) ||
                (iaChoice.equals("papel") && playerChoice.equals("pedra")) ||
                (iaChoice.equals("tesoura") && playerChoice.equals("papel"));
    }

    public String getIaChoice() {
        return iaChoice;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getIaWins() {
        return iaWins;
    }

    public int getConsecutiveWins() {
        return consecutiveWins;
    }

    public void resetGame() {
        playerWins = 0;
        iaWins = 0;
        consecutiveWins = 0;
    }
}
