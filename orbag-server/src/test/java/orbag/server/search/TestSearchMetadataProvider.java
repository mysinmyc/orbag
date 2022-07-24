package orbag.server.search;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestSearchMetadataProvider extends StaticMetadataProvider {

	public TestSearchMetadataProvider() {
		super(TestSearchCi.class);
	}
}
