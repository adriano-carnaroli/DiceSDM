package br.edu.ifsp.scl.sdm.dicesdm.view;

import androidx.appcompat.app.AppCompatActivity;

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

    private Spinner numDadosSpinner;
    private EditText numFacesEditText;
    private Button salvarConfigButton;
    private ConfigurationController configurationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        // Recuperando referência para o numFacesEditText do arquivo de layout
        numFacesEditText = findViewById(R.id.numFacesEditText);
        numDadosSpinner = findViewById(R.id.numDadosSpinner);
        salvarConfigButton = findViewById(R.id.btnSalvarConfig);
        salvarConfigButton.setOnClickListener(this);

        configurationController = new ConfigurationController(this);
        configurationController.buscaConfiguracao();
    }

    public void onClick(View view) {
        Integer numeroFaces = Integer.parseInt(numFacesEditText.getText().toString());
        Integer numeroDados = Integer.parseInt(numDadosSpinner.getSelectedItem().toString());

        Configuration conf = new Configuration(numeroDados, numeroFaces);

        configurationController.salvaConfiguracao(conf);

        Toast.makeText(this, "Configuração salva!", Toast.LENGTH_SHORT).show();
        finishActivity(1);
    }

    public void atualizaView(Configuration configuracao) {
        // Ajusta layout conforme configuração
        numFacesEditText.setText(configuracao.getNumFaces().toString());
        numDadosSpinner.setSelection(configuracao.getNumDados() - 1);

        // Setar resultado para a main activity
        setResult(AppCompatActivity.RESULT_OK, new Intent().putExtra(CONFIGURACAO, configuracao));
    }
}
