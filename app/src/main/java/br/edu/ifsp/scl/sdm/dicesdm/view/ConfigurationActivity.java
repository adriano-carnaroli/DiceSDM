package br.edu.ifsp.scl.sdm.dicesdm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifsp.scl.sdm.dicesdm.R;
import br.edu.ifsp.scl.sdm.dicesdm.controller.ConfigurationController;
import br.edu.ifsp.scl.sdm.dicesdm.model.Configuration;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String CONFIGURACAO = "CONFIGURACAO";

    private EditText qtdNumerosEditText;
    private Button salvarConfigButton;
    private ConfigurationController configurationController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.configuracoes));
        setSupportActionBar(toolbar);

        // Recuperando referência para o numFacesEditText do arquivo de layout
        qtdNumerosEditText = findViewById(R.id.qtdNumerosEditText);
        salvarConfigButton = findViewById(R.id.btnSalvarConfig);
        salvarConfigButton.setOnClickListener(this);

        configurationController = new ConfigurationController(this);
        configurationController.buscaConfiguracao();
    }

    public void onClick(View view) {
        Integer qtdNumeros = Integer.parseInt(qtdNumerosEditText.getText().toString());

        Configuration conf = new Configuration(qtdNumeros);

        configurationController.salvaConfiguracao(conf);

        Toast.makeText(this, "Configuração salva!", Toast.LENGTH_SHORT).show();
        finishActivity(1);
    }

    public void atualizaView(Configuration configuracao) {
        // Ajusta layout conforme configuração
        qtdNumerosEditText.setText(configuracao.getQtdNumeros().toString());

        // Setar resultado para a main activity
        setResult(AppCompatActivity.RESULT_OK, new Intent().putExtra(CONFIGURACAO, configuracao));
    }
}
