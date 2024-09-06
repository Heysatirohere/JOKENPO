package br.com.aula.joken;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
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

    public void selectOption(String selectOption) {
        ImageView resultImg = findViewById(R.id.imagemPadrao);
        TextView resultTxt = findViewById(R.id.textResult);


        // Lógica da escola pela Maquina
            int number = new Random().nextInt(3);
            String [] option = {"pedra", "papel", "tesoura"};
            String optionIA = option[number];

        // Logica mudança de figura de acordo com a oção
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

        // Logica do Jokenpo

        if (
                (optionIA == "tesoura" && selectOption == "papel") ||
                        (optionIA == "papel" && selectOption == "pedra") ||
                        (optionIA == "pedra" && selectOption == "tesoura")

        ) {
            resultTxt.setText(R.string.appJogogameover);
        } else if (
                (optionIA == "papel" && selectOption == "tesoura") ||
                        (optionIA == "pedra" && selectOption == "papel") ||
                        (optionIA == "tesoura" && selectOption == "pedra")

        ) {
            resultTxt.setText(R.string.appJogowin);
        }

    }
}