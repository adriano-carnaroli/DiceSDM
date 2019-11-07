package br.edu.ifsp.scl.sdm.dicesdm.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import br.edu.ifsp.scl.sdm.dicesdm.R;
import br.edu.ifsp.scl.sdm.dicesdm.controller.ConfigurationController;
import br.edu.ifsp.scl.sdm.dicesdm.model.Configuration;
import br.edu.ifsp.scl.sdm.dicesdm.model.ConfigurationService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private final static int CONFIGURACOES_REQUEST_CODE = 0;

    // Random usado para simular o lançamento do dado
    private Random geradorRandomico;

    // Componentes visuais
    private TextView resultadoTextView;
    private Button jogarDadoButton;
    private ImageView resultadoImageView;
    private ImageView resultado2ImageView;
    private Toolbar toolbar;
    private Integer numDados;
    private Integer numFaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        // Após a criação da tela
        geradorRandomico = new Random(System.currentTimeMillis());
        // Recuperando referência para o resultadoTextView do arquivo de layout
        resultadoTextView = findViewById(R.id.resultadoTextView);
        // Recuperando referência para o jogarDadoButton do arquivo de layout
        jogarDadoButton = findViewById(R.id.jogarDadoButton);
        jogarDadoButton.setOnClickListener(this);
        // Recuperando referência para o resultadoImageView do arquivo de layout
        resultadoImageView = findViewById(R.id.resultadoImageView);
        resultado2ImageView = findViewById(R.id.resultado2ImageView);

        ConfigurationService config = new ConfigurationService(this);
        Configuration configuration = config.getConfiguracao();
        numFaces = configuration.getNumFaces();
        numDados = configuration.getNumDados();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.jogarDadoButton) {
            // String que armazena números sorteados
            String resultadoText = getString(R.string.faces_sorteadas);
            if (numFaces > 6) {
                resultadoImageView.setVisibility(View.GONE);
                resultado2ImageView.setVisibility(View.GONE);
            }
            else {
                resultadoImageView.setVisibility(View.VISIBLE);
                // Visibilidade do resultado2ImageView de acordo com número de dados
                if (numDados == 2) {
                    resultado2ImageView.setVisibility(View.VISIBLE);
                }
                else {
                    resultado2ImageView.setVisibility(View.GONE);
                    resultadoText = getString(R.string.face_sorteada);
                }
            }
            // Sorteando números de acordo com número de dados
            for (int i = 1; i <= numDados; i++) {
                int resultado = geradorRandomico.nextInt(numFaces) + 1;
                resultadoText += resultado + ", ";
                ImageView iv = (i == 1) ? resultadoImageView : resultado2ImageView;
                setImageResource(iv, resultado);
            }
            resultadoTextView.setText(resultadoText.substring(0,
                    resultadoText.lastIndexOf(',')));
        }
    }

    private void setImageResource(ImageView iv, int face) {
        String nomeRes = "dice_" + face;
        int idRes = getResources().getIdentifier(nomeRes, "drawable", getPackageName());
        iv.setImageResource(idRes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean retorno = false;
        switch (item.getItemId()) {
            case R.id.configuracoesMenuItem:
                retorno = true;
                Intent intent = new Intent(this, ConfigurationActivity.class);
                startActivityForResult(intent, CONFIGURACOES_REQUEST_CODE);
        }

        return retorno;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CONFIGURACOES_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK) {
            Configuration configuracao = (Configuration) data.getSerializableExtra(ConfigurationActivity.CONFIGURACAO);
            if (configuracao != null) {
                numDados = configuracao.getNumDados();
                numFaces = configuracao.getNumFaces();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
