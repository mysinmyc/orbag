package orbag.server.view;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestViewMetadataProvider extends StaticMetadataProvider {

	public TestViewMetadataProvider() {
		super(TestViewCi.class);
	}
}
