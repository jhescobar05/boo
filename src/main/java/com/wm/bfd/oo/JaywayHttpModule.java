package com.wm.bfd.oo;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.oo.api.OOInstance;
import com.wm.bfd.oo.exception.BFDOOException;

public class JaywayHttpModule extends AbstractModule {

    private static ClientConfig CLIENT = null;
    private String yaml;

    public JaywayHttpModule(String yaml) {
	this.yaml = yaml;
    }

    @Override
    protected void configure() {
	// bind(ClientConfig.class);
	// bind(OOInstance.class);
    }

    @Provides
    @Singleton
    ClientConfig getClientConfig() throws JsonParseException,
	    JsonMappingException, FileNotFoundException, IOException {
	// test
	if (CLIENT == null)
	    CLIENT = new ClientConfig(this.yaml);
	return CLIENT;
    }

    @Provides
    OOInstance getOOInstance() throws JsonParseException, JsonMappingException,
	    FileNotFoundException, IOException {
	OOInstance instance = new OOInstance();
	ClientConfig client = this.getClientConfig();
	instance.setAuthtoken(client.getConfig().getBoo().getApikey());
	instance.setOrgname(client.getConfig().getBoo().getOrg());
	instance.setEndpoint(client.getConfig().getBoo().getHost());
	return instance;
    }

}
