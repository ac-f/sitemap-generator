package com.acf;

public class SitemapUrl {
  private String url;
  private String comment;

  public SitemapUrl(String url, String comment) {
    this.url = url;
    this.comment = comment;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
