package orbag.server.create;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestCreateMetadataProvider extends StaticMetadataProvider {

	public TestCreateMetadataProvider() {
		super(TestCreateCi.class);
	}
}
