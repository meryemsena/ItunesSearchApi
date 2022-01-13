package com.sena.itunes.model;

import java.util.List;

/**
 * Created by Sena KILIÇ on 1/12/2022.
 */

public class ResultContent {
    private List<ItemContent> itemContents;

    public List<ItemContent> getItemContents() {
        return itemContents;
    }

    public void setItemContents(List<ItemContent> itemContents) {
        this.itemContents = itemContents;
    }

    public static class ItemContent {
        private String kind;
        private String wrapperType;
        private String artistName;
        private String trackName;
        private String artworkUrl100;
        private String collectionName;
        private String previewUrl;

        public String getKind() {
            if (kind != null) {
                return kind;
            } else {
                return "";
            }
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getWrapperType() {
            if (wrapperType != null) {
                return wrapperType;
            } else {
                return "";
            }
        }

        public void setWrapperType(String wrapperType) {
            this.wrapperType = wrapperType;
        }

        public String getArtistName() {
            if (artistName != null) {
                return artistName;
            } else {
                return "";
            }
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getTrackName() {
            if (trackName != null) {
                return trackName;
            } else {
                return "";
            }
        }

        public void setTrackName(String trackName) {
            this.trackName = trackName;
        }

        public String getArtworkUrl100() {
            if (artworkUrl100 != null) {
                return artworkUrl100;
            } else {
                return "";
            }
        }

        public void setArtworkUrl100(String artworkUrl100) {
            this.artworkUrl100 = artworkUrl100;
        }

        public String getCollectionName() {
            if (collectionName != null) {
                return collectionName;
            } else {
                return "";
            }
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }

        public String getPreviewUrl() {
            if (previewUrl != null) {
                return previewUrl;
            } else {
                return "";
            }
        }

        public void setPreviewUrl(String previewUrl) {
            this.previewUrl = previewUrl;
        }
    }
}
