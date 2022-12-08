package Classes.Packet.Actions;

import Classes.Packet.Output;
import Classes.Packet.Site;
import Classes.fileio.ActionInput;

public class Purchase implements Action{
    private String type;
    private String feature;
    private String objectType;
    private String movie;
    private String page;
    private Site site;

    public Purchase(ActionInput input, Site site) {
        type = input.getType();
        feature = input.getFeature();
        objectType = input.getObjectType();
        movie = input.getMovie();
        page = input.getPage();
        this.site = site;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }


    @Override
    public Output accept(ActionVisitor visitor) {
        return visitor.visit(this, site);
    }
}