package orbag.server.action;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestActionMetadataProvider extends StaticMetadataProvider {

	public TestActionMetadataProvider() {
		super(TestActionCi.class);
	}
}
