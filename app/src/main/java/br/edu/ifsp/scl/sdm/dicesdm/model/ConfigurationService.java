package br.edu.ifsp.scl.sdm.dicesdm.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfigurationService {

    private static final String NOME_ARQUIVO = "configuracoes";
    private static final int MODO_ARQUIVO = Context.MODE_PRIVATE;
    private static final String TAG_CONFIGURACAO = "configuracoes";

    SharedPreferences sharedPreferences;
    Gson gson;

    public ConfigurationService(Context context){
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO, MODO_ARQUIVO);
        gson = new GsonBuilder().create();
    }

    public void setConfiguracao(Configuration configuration) {
        JSONObject configJson = null;
        try {
            configJson = new JSONObject(gson.toJson(configuration));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString(TAG_CONFIGURACAO, configJson.toString());
        spEditor.commit();
    }

    public Configuration getConfiguracao() {
        String configString = sharedPreferences.getString(TAG_CONFIGURACAO, "");
        return (configString != "") ? gson.fromJson(configString, Configuration.class) : new Configuration();
    }
}
