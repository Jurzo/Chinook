package model;

public class Artist {
	
	private long id;
	private String name;
	
	public Artist(long id, String name) {
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
        return other instanceof Artist && ((Artist) other).getId() == this.id;
    }

	public void setId(int id) {
		this.id = id;
	}

}
