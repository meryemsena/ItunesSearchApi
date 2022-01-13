package com.sena.itunes.data.remote;

/**
 * Created by Sena KILIÇ on 1/10/2022.
 */

public class SearchResponse {
    public String wrapperType;
    public String kind;
    public long artistId;
    public long collectionId;
    public long trackId;
    public String artistName;
    public String collectionName;
    public String trackName;
    public String collectionCensoredName;
    public String trackCensoredName;
    public String artistViewUrl;
    public String collectionViewUrl;
    public String trackViewUrl;

    public String previewUrl;
    public String artworkUrl60;
    public String artworkUrl100;
    public float collectionPrice;
    public float trackPrice;
    public String collectionExplicitness;
    public String trackExplicitness;
    public int discCount;
    public int discNumber;
    public int trackCount;
    public int trackNumber;
    public int trackTimeMillis;
    public String country;
    public String currency;
    public String primaryGenreName;

    public String getWrapperType() {
        return wrapperType;
    }

    public String getKind() {
        return kind;
    }

    public long getArtistId() {
        return artistId;
    }

    public long getCollectionId() {
        return collectionId;
    }

    public long getTrackId() {
        return trackId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public float getCollectionPrice() {
        return collectionPrice;
    }

    public float getTrackPrice() {
        return trackPrice;
    }

    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    public int getDiscCount() {
        return discCount;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public int getTrackTimeMillis() {
        return trackTimeMillis;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }
}
