package entidadesE4;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //La opci�n m�s usada con MySQL
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="publication_date")
	private LocalDate publicationDate;
	
	@Column(name="genre")
	private String genre;
		
	@ManyToOne
	@JoinColumn (name = "author_id")
	private Author author;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public LocalDate getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public Author getAuthor() {
		return author;
	}


	public void setAuthor(Author author) {
		this.author = author;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publicationDate=" + publicationDate + ", genre=" + genre
				+ ", author=" + author + "]";
	}
	
}
