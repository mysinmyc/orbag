package orbag.server.list;

import org.springframework.stereotype.Component;

import orbag.metadata.StaticMetadataProvider;

@Component
public class TestListMetadataProvider extends StaticMetadataProvider {

	public TestListMetadataProvider() {
		super(TestListCi.class);
	}
}
