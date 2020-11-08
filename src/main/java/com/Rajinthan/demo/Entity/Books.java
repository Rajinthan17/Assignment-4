package com.Rajinthan.demo.Entity;

public class Books {
	
	private int id;
	private String title;
	private String author;
	private String url;
	private String isbNumber;
	private int price;
	private String language;
	private String genere;
	
	public Books (int id, String title, String author, String url, String isbNumber, int price, String language, String genere) {
		this.id = id;
		this.title = title;
		this.author= author;
		this.url = url;
		this.isbNumber = isbNumber;
		this.price = price;
		this.language = language;
		this.genere = genere;
		
		
	}

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsbNumber() {
		return isbNumber;
	}

	public void setIsbNumber(String isbNumber) {
		this.isbNumber = isbNumber;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", title=" + title + ", author=" + author + ", url=" + url + ", isbNumber="
				+ isbNumber + ", price=" + price + ", language=" + language + ", genere=" + genere + "]";
	}
	
	
}
