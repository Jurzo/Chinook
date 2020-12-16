package model;

public class Album {
	
	private long id;
	private long artistId;
	private String title;
	
	public Album(long id, String title, long artistId) {
		this.id = id;
		this.title = title;
		this.artistId = artistId;
	}
	
	public long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public long getArtistId() {
		return artistId;
	}
	
	@Override
    public boolean equals(Object other) {
        return other instanceof Album && ((Album) other).getId() == this.id;
    }

}
