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
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.edu.ifsp.scl.sdm.dicesdm.R;
import br.edu.ifsp.scl.sdm.dicesdm.controller.ConfigurationController;
import br.edu.ifsp.scl.sdm.dicesdm.model.Configuration;
import br.edu.ifsp.scl.sdm.dicesdm.model.ConfigurationService;

public class MainActivity extends AppCompatActivity  {

    private final static int CONFIGURACOES_REQUEST_CODE = 0;

    // Random usado para simular o lançamento do dado
    private Random geradorRandomico;

    // Componentes visuais
    private TextView resultadoTextView;
    private TextView numeroSorteadoTextView;
    private Button sortearNumeroButton;
    private Button resetarButton;

    private Toolbar toolbar;
    private Integer qtdNumeros;
    private List<Integer> numerosSorteados = new ArrayList<>();

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
        numeroSorteadoTextView = findViewById(R.id.sorteioTextView);
        // Recuperando referência para o jogarDadoButton do arquivo de layout
        sortearNumeroButton = findViewById(R.id.jogarDadoButton);
        sortearNumeroButton.setOnClickListener(getOnClickSortear());

        resetarButton = findViewById(R.id.resetarButton);
        resetarButton.setOnClickListener(getOnClickResetar());

        ConfigurationService config = new ConfigurationService(this);
        Configuration configuration = config.getConfiguracao();
        qtdNumeros = configuration.getQtdNumeros();
    }

    @NotNull
    private View.OnClickListener getOnClickSortear() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String que armazena números sorteados
                String resultadoText = getString(R.string.numeros_sorteados);

                // Sorteando números
                Integer resultado;
                if (numerosSorteados.size() == qtdNumeros) {
                    Toast.makeText(MainActivity.this, getString(R.string.todos_os_numeros_foram_sorteados), Toast.LENGTH_SHORT).show();
                } else {
                    do {
                        resultado = geradorRandomico.nextInt(qtdNumeros) + 1;
                    } while (numerosSorteados.contains(resultado));
                    resultadoText += " " + numerosSorteados.toString();
                    resultadoTextView.setText(resultadoText.replace("[", "").replace("]", ""));
                    numeroSorteadoTextView.setText(resultado.toString());
                    numerosSorteados.add(resultado);
                }
            }
        };
    }

    @NotNull
    private View.OnClickListener getOnClickResetar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numerosSorteados = new ArrayList<>();
                resultadoTextView.setText(getString(R.string.o_bingo_ainda_nao_comecou));
                numeroSorteadoTextView.setText("");
            }
        };
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
                qtdNumeros = configuracao.getQtdNumeros();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
