package model;

public class Track {

	private long id;
	private String name;

	public Track(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
    public boolean equals(Object other) {
        return other instanceof Track && ((Track) other).getId() == this.id;
    }

}
