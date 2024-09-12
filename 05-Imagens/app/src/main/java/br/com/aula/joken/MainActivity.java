package br.com.aula.joken;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void selectPaper(View view) {
        this.selectOption("papel");
    }

    public void selectRock(View view) {
        this.selectOption("pedra");
    }

    public void selectScissor(View view) {
        this.selectOption("tesoura");
    }

    private int iaWins;
    private int playerWins;
    private int consecutiveWins;

    public void selectOption(String selectOption) {

        ImageView resultImg = findViewById(R.id.imagemPadrao);
        TextView resultTxt = findViewById(R.id.textResult);

        // Lógica da escolha pela Máquina
        int number = new Random().nextInt(3);
        String[] option = {"pedra", "papel", "tesoura"};
        String optionIA = option[number];

        // Lógica de mudança de imagem de acordo com a opção
        switch (optionIA) {
            case "pedra":
                resultImg.setImageResource(R.drawable.pedra);
                break;
            case "papel":
                resultImg.setImageResource(R.drawable.papel);
                break;
            case "tesoura":
                resultImg.setImageResource(R.drawable.tesoura);
                break;
        }

        // Lógica do Jokenpo (com Boolean)
        boolean tesouraEPapel = optionIA == "tesoura" && selectOption == "papel";
        boolean papelEPedra = optionIA == "papel" && selectOption == "pedra";
        boolean pedraETesoura = optionIA == "pedra" && selectOption == "tesoura";

        boolean papelETesoura = optionIA == "papel" && selectOption == "tesoura";
        boolean pedraEPapel = optionIA == "pedra" && selectOption == "papel";
        boolean tesouraEPedra = optionIA == "tesoura" && selectOption == "pedra";

        if (tesouraEPapel || papelEPedra || pedraETesoura) {
            iaWins++;
            resultTxt.setText(R.string.appJogogameover);
            consecutiveWins = 0;

        } else if (papelETesoura || pedraEPapel || tesouraEPedra) {
            playerWins++;
            resultTxt.setText(R.string.appJogowin);
            consecutiveWins = 0;
        }


        TextView placarTxt = findViewById(R.id.textBestOf3);
        placarTxt.setText("Placar - Você: " + playerWins + " IA: " + iaWins);


        if (playerWins == 3) {
            resultTxt.setText("Você venceu a melhor de 3");
            playerWins = 0;
            iaWins = 0;
        } else if (iaWins == 3) {
            resultTxt.setText("Você perdeu a melhor de 3");
            playerWins = 0;
            iaWins = 0;
        }
    }

    public void resetGame(View view) {
        ImageView resultImg = findViewById(R.id.imagemPadrao);
        resultImg.setImageResource(R.drawable.padrao);

        TextView resultTxt = findViewById(R.id.textResult);
        resultTxt.setText("Faça sua escolha!");

        playerWins = 0;
        iaWins = 0;
        consecutiveWins = 0;


        TextView placarTxt = findViewById(R.id.textBestOf3);
        placarTxt.setText("Placar - Você: 0 IA: 0");
    }
}
