package com.sharfah.util.sql;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class StreamingCsvResultSetExtractorTest {

  @Test
  public void testExtractData() throws SQLException {
    final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    final StreamingCsvResultSetExtractor extractor = new StreamingCsvResultSetExtractor(bos);
    final ResultSet rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {
          { "Alice", 20 },
          { "Bob", 35 },
          { "Charles", 50 }
      });
    extractor.extractData(rs);
    final String csv = new String(bos.toByteArray());
    assertThat(csv, is(String.join(System.lineSeparator(), "name,age", "Alice,20", "Bob,35", "Charles,50") + System.lineSeparator()));
  }

  @Test
  public void testEmptyResultSet() throws SQLException {
    final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    final StreamingCsvResultSetExtractor extractor = new StreamingCsvResultSetExtractor(bos);
    final ResultSet rs = MockResultSet.create(new String[] { "name", "age" },
        new Object[][] {});
    extractor.extractData(rs);
    final String csv = new String(bos.toByteArray());
    assertThat(csv, is("name,age" + System.lineSeparator()));
  }
}
