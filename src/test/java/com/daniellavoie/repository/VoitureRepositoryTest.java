package com.daniellavoie.repository;

import org.junit.Assert;
import org.junit.Test;

import com.daniellavoie.elk.model.Voiture;
import com.daniellavoie.elk.repository.VoitureRepository;
import com.daniellavoie.elk.util.ElasticsearchUtil;

public class VoitureRepositoryTest {
	private VoitureRepository voitureRepository;

	public VoitureRepositoryTest() {
		this.voitureRepository = new VoitureRepository(ElasticsearchUtil.getClient());
	}

	@Test
	public void testIndexVoiture() {
		Voiture voiture = new Voiture("renault", "clio", 2005, "bleu", 7);

		Assert.assertNotNull(voitureRepository.indexVoiture(voiture));
	}

	@Test
	public void testSearchVoiture() {
		Assert.assertTrue(!voitureRepository.searchVoiture("renault").isEmpty());
	}
}
