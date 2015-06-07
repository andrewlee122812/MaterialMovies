package com.yiyo.materialmovies.domain;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.yiyo.materialmovies.common.utils.BusProvider;
import com.yiyo.materialmovies.model.MediaDataSource;
import com.yiyo.materialmovies.model.entities.ConfigurationResponse;

/**
 * Created by sumset on 5/06/15.
 */
public class ConfigurationUseCaseController implements ConfigurationUseCase {

    private final String DESIRED_QUALITY = "w780";
    private final MediaDataSource movieDataSource;
    private final Bus uiBus;

    public ConfigurationUseCaseController(MediaDataSource mediaDataSource, Bus uiBus) {
        this.movieDataSource = mediaDataSource;
        this.uiBus = uiBus;

        BusProvider.getRestBusInstance().register(this);
    }

    @Subscribe
    @Override
    public void onConfigurationReceived(ConfigurationResponse configuration) {
        BusProvider.getRestBusInstance().unregister(this);
        configure(configuration);
    }

    @Override
    public void configure(ConfigurationResponse configuration) {
        String url = "";

        if (configuration.getImages() != null) {
            url = configuration.getImages().getBaseUrl();
            String imageQuality = "";

            for (String quality : configuration.getImages().getBackdropSizes()) {
                if (quality.equals(DESIRED_QUALITY)) {
                    imageQuality = DESIRED_QUALITY;
                    break;
                }
            }

            if (imageQuality.equals("")) {
                imageQuality = "original";
            }
            url += imageQuality;
        }
        uiBus.post(url);
    }

    @Override
    public void execute() {
        movieDataSource.getConfiguration();
    }
}
