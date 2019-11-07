package br.edu.ifsp.scl.sdm.dicesdm.controller;

import br.edu.ifsp.scl.sdm.dicesdm.model.Configuration;
import br.edu.ifsp.scl.sdm.dicesdm.model.ConfigurationService;
import br.edu.ifsp.scl.sdm.dicesdm.view.ConfigurationActivity;

public class ConfigurationController {

    private ConfigurationService model;
    private ConfigurationActivity view;

    public ConfigurationController(ConfigurationActivity view) {
        this.view = view;
        model = new ConfigurationService(view.getApplicationContext());
    }

    public void salvaConfiguracao(Configuration configuracao) {
        model.setConfiguracao(configuracao);
        view.atualizaView(configuracao);
        view.finish();
    }

    public void buscaConfiguracao() {
        Configuration configuracao = model.getConfiguracao();
        view.atualizaView(configuracao);
    }
}
