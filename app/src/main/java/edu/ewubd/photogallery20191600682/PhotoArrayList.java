package edu.ewubd.photogallery20191600682;

public class PhotoArrayList {
    String photoId;
    String desc;

    public PhotoArrayList(String photoId, String desc) {
        this.photoId = photoId;
        this.desc = desc;
    }

    public String gePhotoId() {
        return photoId;
    }

    public String getDesc() {
        return desc;
    }

}
