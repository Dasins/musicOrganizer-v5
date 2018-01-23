import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    // Indica si el reproductor esta en uso: True: en uso. False: sin uso.
    private boolean reproduciendo;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        reproduciendo = false;
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(reproduciendo) {
            System.out.println("Hay una reproduccion en curso.");
        }
        else if(indexValid(index)) {
            Track track = tracks.get(index);
            track.increasePlayCount();
            player.startPlaying(track.getFilename());
            reproduciendo = true;
            System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
        }
    }
    
    /**
     * Informa por pantalla si se esta reproduciendo musica
     */
    public void isPlaying() {
        String aImprimir;
        if(reproduciendo) {
            aImprimir = "Nada en el reproductor";
        }
        else {
            aImprimir = "Reproductor en uso";
        }
        System.out.println(aImprimir);
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Enumera todas las pistas que contengan la cadena de busqueda.
     * @param searchString La cadena de busqueda que hay que encontrar.
     */
    public void findInTitle(String searchString) {
        for(Track track : tracks) {
            String title = track.getTitle();
            if(title.contains(searchString)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(reproduciendo) {
            System.out.println("Hay una reproduccion en curso.");
        }
        else if(tracks.size() > 0) {
            Track track = tracks.get(0);
            track.increasePlayCount();
            player.startPlaying(track.getFilename());
            reproduciendo = true;
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        reproduciendo = false;
    }

    /**
     * Cambia el album de una cancion
     * @param index La posicion de la cancion en la coleccion.
     * @param album El album nuevo.
     */
    public void setAlbum(int index, String album) {
        if(indexValid(index)) {
            tracks.get(index).setAlbum(album);
        }
    }
    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    
    public void listAllTrackWithIterator() {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()) {
            Track track = it.next();
            System.out.println(track.getDetails());
        }
    }
    
    public void removeByArtist(String artistaABorrar) {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()) {
            Track track = it.next();
            String artista = track.getArtist();
            if(artista.equals(artistaABorrar)) {
                it.remove();
            }
        }
    }
    
    public void removeByTitle(String tituloBuscado) {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()) {
            Track track = it.next();
            String titulo = track.getTitle();
            if(titulo.contains(tituloBuscado)) {
                it.remove();
            }
        }
    }
}
