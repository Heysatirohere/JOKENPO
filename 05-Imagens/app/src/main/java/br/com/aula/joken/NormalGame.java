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

public class NormalGame extends AppCompatActivity {

    private Jokenpo jokenpo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_normal_game);

        Button home = findViewById(R.id.buttonHome_normalActivity);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NormalGame.this, MainActivity.class);
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
        ImageView imagemPadrao = findViewById(R.id.imagemPadrao_normalActivity);
        TextView textResult = findViewById(R.id.textResult_normalActivity);

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
        TextView consecutiveWinsText = findViewById(R.id.textWinCount_normalActivity);
        consecutiveWinsText.setText("Vitórias consecutivas: " + jokenpo.getConsecutiveWins());
    }

    public void resetGame(View view) {
        ImageView imagemPadrao = findViewById(R.id.imagemPadrao_normalActivity);
        imagemPadrao.setImageResource(R.drawable.padrao);

        TextView textResult = findViewById(R.id.textResult);
        textResult.setText("Faça sua escolha!");

        jokenpo.resetGame();
        updateScoreboard();

    }

}
