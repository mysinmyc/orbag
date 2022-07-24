package orbag.server.metadata;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestMetadataMetadataProvider extends StaticMetadataProvider {

	public TestMetadataMetadataProvider() {
		super(TestMetadataCi.class);
	}
}
