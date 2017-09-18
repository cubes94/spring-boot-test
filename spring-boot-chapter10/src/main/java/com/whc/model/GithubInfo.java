package com.whc.model;

/**
 * Created by whc on 2017/9/18.
 */
public class GithubInfo {

    private String author;

    private String name;

    private String readme;

    public GithubInfo() {
    }

    public GithubInfo(String author, String name, String readme) {
        this.author = author;
        this.name = name;
        this.readme = readme;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }
}
