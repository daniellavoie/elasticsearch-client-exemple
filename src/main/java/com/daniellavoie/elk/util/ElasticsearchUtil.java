package com.daniellavoie.elk.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public abstract class ElasticsearchUtil {
	public static Client getClient() {
		try {
			// Définition du cluster name.
			Settings settings = Settings.settingsBuilder().put("cluster.name", "formation-elk").build();

			// Instanciation du Transport Client.
			TransportClient client = TransportClient.builder().settings(settings).build();

			// Configuration d'un noeud ES.
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.99.100"), 9300));

			return client;
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}
}
