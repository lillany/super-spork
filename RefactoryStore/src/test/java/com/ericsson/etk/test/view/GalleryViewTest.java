package com.ericsson.etk.test.view;

import com.ericsson.etk.test.storage.DataStorage;
import org.junit.Test;

/**
 * Created by eadrdam on 06.07.17..
 */
public class GalleryViewTest{
    @Test
    public void testGetView() throws Exception {
        String path = this.getClass().getClassLoader().getResource("data.json").getPath();
        DataStorage storage = new DataStorage(path);
        GalleryView galleryView = new GalleryView();
        String result = galleryView.getView(null, "Martina Reeves", null, null, null);
        System.out.println("****************************");
        System.out.println(result);
    }

}