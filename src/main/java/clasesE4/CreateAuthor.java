package clasesE4;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidadesE4.Author;
import entidadesE4.Book;

public class CreateAuthor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

				Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Author.class )
			    .addAnnotatedClass( Book.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
				
		Session session = sessionFactory.openSession();
				
				
		try {			
			// crea un objeto Author
			System.out.println("Creando un nuevo objeto Author");
			
			Author newAuthor = new Author();
			
			newAuthor.setFirstName("Gorka");
			newAuthor.setLastName("Laspiur");
			newAuthor.setNationality("española");
			newAuthor.setBirthdate(LocalDate.parse("1985-04-04"));
			
			// comienza la transacci�n
			session.beginTransaction();
						
			// guarda el objeto Author
			session.persist(newAuthor);
						
			// hace commit de la transaccion
			session.getTransaction().commit();
								
			System.out.println("Autor creado correctamente");
		}

		catch ( Exception e ) {
			// rollback ante alguna excepci n
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
	
}
