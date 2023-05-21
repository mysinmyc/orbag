package orbag.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import orbag.EnableOrbagCore;
import orbag.data.PaginationInfo;
import orbag.search.Operator;
import orbag.search.SearchConditions;





@DataJpaTest
public class HibernateRepositoryTest {

	@EnableAutoConfiguration
	@SpringBootConfiguration
	@EnableOrbagCore
	@ComponentScan(basePackages = "orbag")
	public static class ConfigurationClass {
		
	}

	@PersistenceContext
	EntityManager entityManager;

	@BeforeEach
	public void fillData() {
		
		for (int cnt=0;cnt<10;cnt++) {
			TestEntity testEntity = new TestEntity();
			testEntity.setStringField("valore di "+cnt);
			testEntity.setIntegerField(cnt);
			testEntity.setLongField(Long.valueOf(cnt));
			entityManager.persist(testEntity);
		}

	}
	
	List<TestEntity> findByConditions(HibernateRepository hibernateRepository, SearchConditions searchConditions) throws Exception {
		List<TestEntity> result = new ArrayList<TestEntity>();
		hibernateRepository.searchByConditionsInto(TestEntity.class, searchConditions, result::add, new PaginationInfo());
		return result;
	}
	
	@Test
	public void testConditions(@Autowired HibernateRepository hibernateRepository) throws Exception{
	
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("stringField", Operator.LIKE, "valore%")).size(),10);
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("stringField", Operator.EQUAL, "valore di 5")).size(),1);
		
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("integerField", Operator.GREATER, 0)).size(),9);
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("integerField", Operator.GREATER_OR_EQUAL, 5)).size(),5);
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("integerField", Operator.LESS, 5)).size(),5);
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("integerField", Operator.LESS_OR_EQUAL, 5)).size(),6);
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("longField", Operator.LESS_OR_EQUAL, 5)).size(),6);
		
		
		assertEquals(findByConditions(hibernateRepository, SearchConditions.and().withCondition("integerField", Operator.LESS, 5).withCondition("integerField", Operator.GREATER, 1)).size(),3);
		
		assertEquals(findByConditions(hibernateRepository, SearchConditions.or().withCondition("integerField", Operator.LESS, 5).withCondition("integerField", Operator.GREATER, 7)).size(),7);
	}
}
