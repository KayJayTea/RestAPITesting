package com.sharfah.util.sql;

import static com.sharfah.util.hamcrest.IsEqualJSON.equalToJSON;

import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class StreamingJsonResultSetExtractorTest {

	@Test
	public void testExtractData() throws SQLException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final StreamingJsonResultSetExtractor extractor = new StreamingJsonResultSetExtractor(bos);
		final ResultSet rs = MockResultSet.create(new String[] { "name", "age" },
				new Object[][] { { "Alice", 20 }, { "Bob", 35 }, { "Charles", 50 } });
		extractor.extractData(rs);
		final String json = new String(bos.toByteArray());
		MatcherAssert.assertThat(json, equalToJSON("[" + "{\"name\":\"Alice\",\"age\":20},"
				+ "{\"name\":\"Bob\",\"age\":35}," + "{\"name\":\"Charles\",\"age\":50}" + "]"));
	}

	@Test
	public void testEmptyResultSet() throws SQLException {
		final ByteArrayOutputStream bos = new ByteArrayOutputStream();
		final StreamingJsonResultSetExtractor extractor = new StreamingJsonResultSetExtractor(bos);
		final ResultSet rs = MockResultSet.create(new String[] { "name", "age" }, new Object[][] {});
		extractor.extractData(rs);
		final String json = new String(bos.toByteArray());
		MatcherAssert.assertThat(json, equalToJSON("[]"));
	}
}
