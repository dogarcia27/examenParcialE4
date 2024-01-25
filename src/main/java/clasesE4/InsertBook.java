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

public class InsertBook {

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
					// busca el objeto Author
					int authorId = 3;	//uso el id 3 porque los dos primeros los he creado mal
					session.beginTransaction();
					Author tempAuthor = session.get(Author.class, authorId); 
					System.out.println(tempAuthor.toString());
					// commit de la  transaccion
					session.getTransaction().commit();
					
					Book newBook = createBook(tempAuthor);
					// comienza la transacci�n
					session.beginTransaction();
								
					// guarda el objeto Author
					session.persist(newBook);
								
					// hace commit de la transaccion
					session.getTransaction().commit();
										
					System.out.println("Libro creado correctamente");
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
	
	public static Book createBook(Author newAuthor) {
		
		Book tempBook = new Book();
		
		tempBook.setTitle("Libro de prueba");
		tempBook.setGenre("Acceso a datos");
		tempBook.setPublicationDate(LocalDate.parse("2024-01-25"));
		tempBook.setAuthor(newAuthor);
		return tempBook;
	}
}

