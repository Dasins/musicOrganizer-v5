/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // El album de la cancion.
    private String album;
    // Where the track is stored.
    private String filename;
    // Numero de veces que ha sido reproducida.
    private int playCount;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Devuelve el album.
     * @return El album.
     */
    public String getAlbum() {
        return album;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
    
    /**
     * Devuelve el numero de reproduciones.
     * @return El numero de veces que se ha reproducido.
     */
    public int getPlayCount() {
        return playCount;
    }
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + " - " + album + "(file: " + filename + ") reproduciones: " + playCount;
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
        this.album = "";
        playCount = 0;
    }
    
    /**
     * Cambia el album de la cancion.
     * @param album El nombre del nuevo album
     */
    public void setAlbum(String album) {
        this.album = album;
    }
    
    /**
     * Reinicia el numero de reproduciones a 0.
     */
    public void resetPlayCount() {
        playCount = 0;
    }
    
    /**
     * Incrementa en 1 el numero de reproduciones.
     */
    public void increasePlayCount() {
        playCount++;
    }
    
}
