package com.yiyo.materialmovies.materialmovies.mvp.views;

/**
 * Created by sumset on 25/05/15.
 */
public interface DetailView extends MVPView {

    public void setImage (String url);

    public void setName (String title);

    public void setDescription(String description);

    public void setHomepage (String homepage);

    public void setCompanies (String companies);

    public void setTagline(String tagline);

    public void finish(String cause);

    public void showConfirmationView ();

    public void animateConfirmationView ();

    public void startClosingConfirmationView();
}
