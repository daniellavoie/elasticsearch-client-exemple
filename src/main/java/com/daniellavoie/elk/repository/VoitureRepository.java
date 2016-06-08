package com.daniellavoie.elk.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.daniellavoie.elk.model.Voiture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VoitureRepository {
	private Client client;
	private ObjectMapper objectMapper;

	public VoitureRepository(Client client) {
		this.client = client;

		objectMapper = new ObjectMapper();
	}

	public boolean delete(String id) {
		try {
			return client.prepareDelete("voiture", "voiture", id).execute().get().isFound();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	public String indexVoiture(Voiture voiture) {
		try {
			String json = objectMapper.writeValueAsString(voiture);

			ActionFuture<IndexResponse> future = client.prepareIndex("voiture", "voiture").setSource(json).execute();

			IndexResponse response = future.get();

			return response.getId();
		} catch (JsonProcessingException | ExecutionException | InterruptedException ex) {
			throw new RuntimeException(ex);
		}
	}

	public List<Voiture> searchVoiture(String query) {
		try {
			// Construction et exécution de la requête de recherche.
			ActionFuture<SearchResponse> responseFuture = client.prepareSearch("voiture")
					.setQuery(QueryBuilders.matchQuery("_all", query)).execute();

			// Récupération de la réponse future.
			SearchResponse response = responseFuture.get();

			// Transcodage des résultats de recherche de JSON vers Java.
			List<Voiture> voitures = new ArrayList<>();
			for (SearchHit searchHit : response.getHits()) {
				voitures.add(objectMapper.readValue(searchHit.getSourceAsString(), Voiture.class));
			}

			return voitures;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
