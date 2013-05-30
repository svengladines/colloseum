package be.occam.colloseum.album;


public abstract class Album {
	
	protected String id;
	
	protected String title;

	public String getId() {
		return id;
	}

	public Album setId(String id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Album setTitle(String title) {
		this.title = title;
		return this;
	}
	
}
