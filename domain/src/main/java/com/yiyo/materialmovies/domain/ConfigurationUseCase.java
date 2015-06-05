package com.yiyo.materialmovies.domain;

import com.yiyo.materialmovies.model.entities.ConfigurationResponse;

/**
 * Created by sumset on 5/06/15.
 */
public interface ConfigurationUseCase extends Usecase {

    void onConfigurationReceived(ConfigurationResponse configuration);

    void configure(ConfigurationResponse configuration);
}
