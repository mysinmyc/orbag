package orbag.server.search;

import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import orbag.data.PaginationInfo;
import orbag.data.TableBuilder;
import orbag.input.SerializableFieldGroup;
import orbag.metadata.ConfigurationItemDescriptor;
import orbag.metadata.MetadataRegistry;
import orbag.search.ResultType;
import orbag.search.SearchContext;
import orbag.search.SearchExecutor;
import orbag.search.SearchExecutorRegistry;
import orbag.visibility.FilterContext;
import orbag.visibility.VisibilityManager;

@Component
public class SearchService {

	@Autowired
	MetadataRegistry metadataRegistry;

	@Autowired
	SearchExecutorRegistry searchExecutorRegistry;

	@Autowired
	VisibilityManager visibilityManager;

	@SuppressWarnings("unchecked")
	private <E> void invokeExecutor(String configurationItemName, Authentication user, PaginationInfo paginationInfo,
			BiConsumer<SearchExecutor<E>, SearchContext> operation) {
		ConfigurationItemDescriptor descriptor = metadataRegistry
				.getConfigurationItemDescriptorByName(configurationItemName);
		if (descriptor == null) {
			throw new RuntimeException("Invalid configuration item type " + configurationItemName);
		}
		SearchExecutor<?> searchExecutor = visibilityManager.findFirstObject(
				searchExecutorRegistry.getAllSearchExecutors(),
				FilterContext.forTargetClass(descriptor.getJavaClass()).forUser(user));
		SearchContext searchContext = new SearchContext();
		searchContext.setConfigurationItemDescriptor(descriptor);
		searchContext.setUser(user);
		searchContext.setPaginationInfo(paginationInfo);
		operation.accept((SearchExecutor<E>) searchExecutor, searchContext);
	}

	public SearchRequest getSearchRequestTemplateFor(String configurationItemName, Authentication user,
			PaginationInfo paginationInfo) {
		SearchRequest requestTemplate = new SearchRequest();
		requestTemplate.setConfigurationItemName(configurationItemName);
		requestTemplate.setResultType(ResultType.HIGHLIGHTED_FIELDS);
		SerializableFieldGroup parametersBuilder = new SerializableFieldGroup();
		invokeExecutor(configurationItemName, user, paginationInfo, (e, c) -> {
			e.buildSearchParameters(parametersBuilder, c);
		});
		requestTemplate.setParameters(parametersBuilder);
		return requestTemplate;
	}

	public <T> void executeSearchInto(SearchRequest request, Authentication user, PaginationInfo paginationInfo,
			TableBuilder<T> builder) {
		invokeExecutor(request.getConfigurationItemName(), user, paginationInfo,
				new BiConsumer<SearchExecutor<T>, SearchContext>() {
					@Override
					public void accept(SearchExecutor<T> e, SearchContext c) {
						e.buildResultColumns(builder, request.getResultType(), c);
						e.executeQuery(request.getParameters(), builder, c);
					}
				});
	}
}
