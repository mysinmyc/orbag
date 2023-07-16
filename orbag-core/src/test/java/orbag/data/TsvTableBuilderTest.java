package orbag.data;

import orbag.EnableOrbagCore;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.util.LimitExceededException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TsvTableBuilderTest {

	@Configuration
	@EnableOrbagCore
	static class ConfigurationClass {

	}

	@Test
	void test(@Autowired ConfigurationItemReferenceService configurationItemReferenceService) throws IOException {

		try (
			 Writer writer = new StringWriter()) {
			TsvTableBuilder<String> tableBuilder = new TsvTableBuilder<String>(writer);
			tableBuilder.addColumn("static", ColumnType.Primitive);
			tableBuilder.addGeneratedColumn("generated", ColumnType.Primitive, (o) -> (o.toString()));

			RowBuilder<String> rowbuilder = tableBuilder.rows();
			for (int cnt = 0; cnt < 10; cnt++) {

				rowbuilder.addRow("ciao" + cnt).withValue("static", cnt*2);
			}


			writer.flush();

			String result =  writer.toString();

			String[] rows = result.split("\n");
			assertArrayEquals(new String[]{
					"static\tgenerated",
					"0\tciao0",
					"2\tciao1",
					"4\tciao2",
					"6\tciao3",
					"8\tciao4",
					"10\tciao5",
					"12\tciao6",
					"14\tciao7",
					"16\tciao8",
			}, rows);
		}

	}
	

}
