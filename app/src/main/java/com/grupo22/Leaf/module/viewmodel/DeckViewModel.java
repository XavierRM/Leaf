package com.grupo22.Leaf.module.viewmodel;

public class DeckViewModel {

    private String id;
    private String title;

    public DeckViewModel(String id,
                           String title) {

        this.id = id;
        this.title = title;
    }

    public String getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

}
