package clasesE4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidadesE4.Author;
import entidadesE4.Book;

public class DeleteBook {

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
							
							
							int bookId = 1;
							
							Book tempBook = session.get(Book.class, bookId);
							// comienza la transacci�n
							session.beginTransaction();
						
							// borra la universidad pero no el estudiante. "on delete set null" en BD
							session.remove(tempBook);
							
							// hace commit de la transaccion
							session.getTransaction().commit();
									
							System.out.println("Libro borrado");
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
