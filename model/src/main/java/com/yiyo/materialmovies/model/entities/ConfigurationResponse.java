package com.yiyo.materialmovies.model.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Class that respresents the configuration in the model layer,
 *
 * The configuration gets the system wide configuration information. Some elements of the API
 * require some knowledge of this configuration data. The purpose of this is to try and keep the
 * actual API responses as light as possible.
 * It is recommended you cache this data every few days.
 */
public class ConfigurationResponse {

    private ConfigurationImages images;
    @SerializedName("change_keys")
    private String[] changeKeys;

    public ConfigurationImages getImages() {
        return images;
    }

    public class ConfigurationImages {
        @SerializedName("base_url")
        private String baseUrl;

        @SerializedName("secure_base_url")
        private String secureBaseUrl;

        @SerializedName("backdrop_sizes")
        private String[] backdropSizes;

        @SerializedName("logo_sizes")
        private String[] logoSizes;

        @SerializedName("poster_sizes")
        private String[] posterSizes;

        @SerializedName("profile_sizes")
        private String[] profileSizes;

        @SerializedName("still_sizes")
        private String[] stillSizes;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getSecureBaseUrl() {
            return secureBaseUrl;
        }

        public void setSecureBaseUrl(String secureBaseUrl) {
            this.secureBaseUrl = secureBaseUrl;
        }

        public String[] getBackdropSizes() {
            return backdropSizes;
        }

        public void setBackdropSizes(String[] backdropSizes) {
            this.backdropSizes = backdropSizes;
        }

        public String[] getLogoSizes() {
            return logoSizes;
        }

        public void setLogoSizes(String[] logoSizes) {
            this.logoSizes = logoSizes;
        }

        public String[] getPosterSizes() {
            return posterSizes;
        }

        public void setPosterSizes(String[] posterSizes) {
            this.posterSizes = posterSizes;
        }

        public String[] getProfileSizes() {
            return profileSizes;
        }

        public void setProfileSizes(String[] profileSizes) {
            this.profileSizes = profileSizes;
        }

        public String[] getStillSizes() {
            return stillSizes;
        }

        public void setStillSizes(String[] stillSizes) {
            this.stillSizes = stillSizes;
        }
    }
}
