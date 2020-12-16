package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Artist;
import model.Track;


public class ChinookDao{
	
	// En tiedä miksi ENV muuttujat eivät tulleet mukana palautukseen, mutta nyt niitä ei tarvitse
	//private static final String JDBC_URL = System.getenv("CHINOOK_URL");
	private String URL;
	
	public ChinookDao() {
		this("jdbc:sqlite:chinook.sqlite");
	}
	
	public ChinookDao(String URL) {
		this.URL = URL;
	}

    private Connection connect() throws SQLException{
        return DriverManager.getConnection(this.URL);
    }

    public List<Artist> getAllArtists() {
    	List<Artist> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Artist ORDER BY Name ASC");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("ArtistId");
				String nimi = tulokset.getString("Name");
				lista.add(new Artist(id, nimi));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Album> getAllAlbums() {
    	List<Album> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Album ORDER BY Title ASC");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("AlbumId");
				long artistId = tulokset.getLong("ArtistId");
				String title = tulokset.getString("Title");
				lista.add(new Album(id, title, artistId));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Track> getAllTracks() {
    	List<Track> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Track ORDER BY Name ASC");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("TrackId");
				String nimi = tulokset.getString("Name");
				lista.add(new Track(id, nimi));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Album> getAlbumsByArtist(long ArtistId) {
    	List<Album> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Album WHERE ArtistId = ? ORDER BY Title ASC");
			kysely.setLong(1, ArtistId);
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("AlbumId");
				long artistId = tulokset.getLong("ArtistId");
				String title = tulokset.getString("Title");
				lista.add(new Album(id, title, artistId));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Album> getAlbumsByTitle(String titleSearch) {
    	List<Album> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT AlbumId, Album.ArtistId, Album.Title, Artist.Name FROM Album \n"
															+ "LEFT JOIN Artist ON Artist.ArtistId = Album.ArtistId \n"
															+ "WHERE Title LIKE ? ORDER BY Title ASC");
			kysely.setString(1, "%" + titleSearch + "%");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long artistId = tulokset.getLong("ArtistId");
				String title = tulokset.getString("Title");
				long id = tulokset.getLong("AlbumId");

				lista.add(new Album(id, title, artistId));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Artist> getArtistsByName(String nameSearch) {
    	List<Artist> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT ArtistId, Name FROM Artist \n"
															+ "WHERE Name LIKE ? \n"
															+ "ORDER BY Name ASC;");
			kysely.setString(1, "%" + nameSearch + "%");
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("ArtistId");
				String nimi = tulokset.getString("Name");
				lista.add(new Artist(id, nimi));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    public List<Track> getTracksByAlbum(long AlbumId) {
    	List<Track> lista = new ArrayList<>();
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("SELECT * FROM Track WHERE AlbumId = ?");
			kysely.setLong(1, AlbumId);
			ResultSet tulokset = kysely.executeQuery();
			
			while (tulokset.next()) {
				long id = tulokset.getLong("TrackId");
				String nimi = tulokset.getString("Name");
				lista.add(new Track(id, nimi));
			}
			tulokset.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return lista;
    }
    
    
    
    public Artist getArtist(long id) {
    	Artist artisti = null;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from Artist where ArtistId = ?");
			kysely.setLong(1, id);
			ResultSet tulos = kysely.executeQuery();
			
			if (tulos.next()) {
				artisti = new Artist(id, tulos.getString("Name"));
			}
			tulos.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return artisti;
    }
    
    public Album getAlbum(long id) {
    	Album albumi = null;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from Album where AlbumId = ?");
			kysely.setLong(1, id);
			ResultSet tulos = kysely.executeQuery();
			
			if (tulos.next()) {
				albumi = new Album(id, tulos.getString("Title"), tulos.getLong("ArtistId"));
			}
			tulos.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return albumi;
    }
    
    public Track getTrack(long id) {
    	Track kappale = null;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("select * from Track where TrackId = ?");
			kysely.setLong(1, id);
			ResultSet tulos = kysely.executeQuery();
			
			if (tulos.next()) {
				kappale = new Track(id, tulos.getString("Name"));
			}
			tulos.close();
			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return kappale;
    }
    

    public boolean addArtist(Artist artisti) {
    	int autoId = -1;
		
		try {
			Connection yhteys = connect();
			PreparedStatement kysely = yhteys.prepareStatement("INSERT INTO Artist (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			kysely.setString(1, artisti.getName());
			
			// Asetetaan tuotteelle tietokannassa asetettu ID
			if(kysely.executeUpdate() == 1) {
				ResultSet tulos = kysely.getGeneratedKeys();
				tulos.next();
				autoId = tulos.getInt(1);
				artisti.setId(autoId);
				tulos.close();
			}

			kysely.close();
			yhteys.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (autoId == -1) return false;
        return true;
    }

}