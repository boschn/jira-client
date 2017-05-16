package net.rcarz.jiraclient;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Test cases for stuff relating to filters.
 */
public class FilterTest {

	@Test
	public void testGetFilter() throws JiraException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		JiraClient jira = new JiraClient("https://jira.atlassian.com/", null);

		String id = "12844";
		Filter filter = jira.getFilter(id);

		assertNotNull(filter);
		assertEquals("with id " + id, id, filter.getId());
		final String expectedName = "All JIRA Bugs";
		assertEquals("with name " + expectedName, expectedName, filter.getName());
		final String expectedJql = "project = 10240 AND issuetype = 1 ORDER BY key DESC";
		assertEquals("with jql: " + expectedJql, expectedJql, filter.getJql());
		assertEquals("None favourite", false, filter.isFavourite());
	}

}
