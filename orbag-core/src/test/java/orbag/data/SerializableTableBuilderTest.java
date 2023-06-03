package orbag.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import orbag.EnableOrbagCore;
import orbag.reference.ConfigurationItemReferenceService;
import orbag.util.LimitExceededException;

@SpringBootTest
@SpringBootConfiguration
@EnableOrbagCore
public class SerializableTableBuilderTest {

	@Test
	void test(@Autowired ConfigurationItemReferenceService configurationItemReferenceService) {

		SerializableTableBuilder<String> tableBuilder = new SerializableTableBuilder<String>(configurationItemReferenceService);
		tableBuilder.setLimit(10);
		tableBuilder.addColumn("static", ColumnType.Primitive);
		tableBuilder.addGeneratedColumn("generated", ColumnType.Primitive, (o) -> (o.toString()));

		RowBuilder<String> rowbuilder= tableBuilder.rows();
		for (int cnt = 0; cnt < 10; cnt++) {
			rowbuilder.addRow("ciao"+cnt).withValue("prima", 1);
		}
		
		assertThrows(LimitExceededException.class, ()->rowbuilder.addRow("too much"));
		SerializableTable table = tableBuilder.build();
		assertEquals(2,table.getColumns().size());
		assertEquals(10,table.getRows().size());
		
		assertEquals("ciao0",table.getRows().get(0).getFields().get("generated"));
	}
	

}
