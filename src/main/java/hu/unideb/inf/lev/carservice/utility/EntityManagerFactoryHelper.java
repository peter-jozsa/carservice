package hu.unideb.inf.lev.carservice.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A helper class which setups only one {@link EntityManagerFactory} and lets consumers get as much EntityManager
 * as they wish.
 */
public class EntityManagerFactoryHelper {
    private static final String PERSISTENCE_UNIT_NAME = "carservice-persistence-unit";

    private static final EntityManagerFactory emFactory;

    static {
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    /**
     * Constructs a new {@link EntityManager} instance.
     * @return A new entity manager instance.
     */
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

    /**
     * Closes all connections, should be called when application is going to be closed.
     */
    public static void close() {
        emFactory.close();
    }
}
