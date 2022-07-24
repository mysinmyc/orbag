package orbag.server.update;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestUpdateMetadataProvider extends StaticMetadataProvider {

	public TestUpdateMetadataProvider() {
		super(TestUpdateCi.class);
	}
}
