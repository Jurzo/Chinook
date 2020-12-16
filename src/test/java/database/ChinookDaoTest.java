package database;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Artist;

class ChinookDaoTest {

    private static final String SRC_URL = System.getenv("SRC");
    private static final String TEST_URL = System.getenv("TEST");
    private ChinookDao dao;


    //Kopioidaan Chinook-tietiokanta, että sitä voidaan testata muuttamatta alkuperäistä tietokantaa
    
    @BeforeEach
    public void setup() {
    	InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(SRC_URL);
            os = new FileOutputStream(TEST_URL);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
            
        } catch (IOException e) {
        	System.out.println(e);
        }
        
        this.dao = new ChinookDao("jdbc:sqlite:" + TEST_URL);
    }
    
    
    @Test
    public void ArtistsListWorks() {
    	List<Artist> artistit = dao.getAllArtists();
    	
    	assertTrue(!artistit.isEmpty());
    }
    
    @Test
    public void AddArtist() {
    	Artist artist = new Artist(1, "Testiartisti");
    	dao.addArtist(artist);
    	assertTrue(dao.getArtistsByName(artist.getName()).contains(artist));
    }
    
    @Test
    public void checkCleanTestDB() {
    	ChinookDao dao2 = new ChinookDao("jdbc:sqlite:" + SRC_URL);
    	assertTrue(dao.getAllArtists().containsAll(dao2.getAllArtists()));
    }
    
    @Test
    public void checkArtistAlbums() {
    	Artist artist = dao.getArtist(3);
    	List<Album> albums = dao.getAlbumsByArtist(artist.getId());
    	for (Album album : albums) {
    		assertTrue(album.getArtistId() == artist.getId());
    	}
    }
}
