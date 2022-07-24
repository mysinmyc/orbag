package orbag.dao;

public interface OrbagWritableRepository extends OrbagRepository {

	void delete(Object object);

	Object create(Object newObject);
}
