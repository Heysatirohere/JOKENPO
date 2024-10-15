package br.com.aula.joken;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BestOf3 extends AppCompatActivity {

    private Jokenpo jokenpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Botão Home

        Button home = findViewById(R.id.buttonHome_bestOf3);
         home.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(BestOf3.this, MainActivity.class);
                 startActivity(intent);
                 finish();
             }
         });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jokenpo = new Jokenpo(); // Inicializa a lógica do jogo
    }

    public void selectPaper(View view) {
        selectOption("papel");
    }

    public void selectRock(View view) {
        selectOption("pedra");
    }

    public void selectScissor(View view) {
        selectOption("tesoura");
    }

    public void selectOption(String playerChoice) {
        ImageView imagemPadrao = findViewById(R.id.imagemPadrao);
        TextView textResult = findViewById(R.id.textResult);

        String result = jokenpo.playRound(playerChoice);
        String iaChoice = jokenpo.getIaChoice();

        switch (iaChoice) {
            case "pedra":
                imagemPadrao.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                imagemPadrao.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                imagemPadrao.setImageResource(R.drawable.tesoura);
                break;
        }

        if (result.equals("player")) {
            textResult.setText("Você ganhou!");
        } else if (result.equals("ia")) {
            textResult.setText("Você perdeu!");
        } else {
            textResult.setText("Empate!");
        }

        updateScoreboard();
    }

    private void updateScoreboard() {
        TextView textWinCount = findViewById(R.id.textBestOf3);
        TextView result = findViewById(R.id.textResult);
        textWinCount.setText("Vitórias: " + jokenpo.getPlayerWins() + " X " + jokenpo.getIaWins());

        if (jokenpo.getPlayerWins() == 3) {
            result.setText("Você ganhou o jogo!");
            jokenpo.resetGame(); // Reseta o jogo ao atingir 3 vitórias
        } else if (jokenpo.getIaWins() == 3) {
            result.setText("Você perdeu o jogo!");
            jokenpo.resetGame(); // Reseta o jogo ao atingir 3 derrotas
        }
    }

    public void resetGame(View view) {
        ImageView imagemPadrao = findViewById(R.id.imagemPadrao);
        imagemPadrao.setImageResource(R.drawable.padrao);

        TextView textResult = findViewById(R.id.textResult);
        textResult.setText("Faça sua escolha!");

        jokenpo.resetGame();
        updateScoreboard();

    }
}
