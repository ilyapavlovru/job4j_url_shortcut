package ru.job4j.url_shortcut.domain;

import java.util.Objects;

public class Site {

    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Objects.equals(this.site, site.site);
    }

    @Override
    public int hashCode() {
        return Objects.hash(site);
    }
}
