package orbag.dao;

public interface OrbagWritableRepository extends OrbagRepository {

	void delete(Object object);

	<T> T create(T newObject);

	<T> T update(T object);
}
