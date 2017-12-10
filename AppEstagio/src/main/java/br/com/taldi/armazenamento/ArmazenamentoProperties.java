package br.com.taldi.armazenamento;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class ArmazenamentoProperties {

    /**
     * Localizacao da pasta para armazenar arquivos
     */
	private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
