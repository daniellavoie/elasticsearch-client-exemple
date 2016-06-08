package com.daniellavoie;

import org.elasticsearch.client.Client;
import org.junit.Assert;
import org.junit.Test;

import com.daniellavoie.elk.util.ElasticsearchUtil;

public class ElasticsearchUtilTest {
	@Test
	public void testGetClient() {
		Client client = ElasticsearchUtil.getClient();

		Assert.assertNotNull(client);
		Assert.assertNotNull(client.admin().indices());
	}
}