package com.acf;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    String filename = "sitemap.xml";
    String lastModify = "2022-11-25";
    List<SitemapUrl> urls = getUrlList();
    String result = generateSitemap(urls, lastModify);
    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    writer.write(result);

    writer.close();
  }

  public static List<SitemapUrl> getUrlList() throws IOException {
    File file = new File("source.txt");
    List<SitemapUrl> list = readSourceFile(new FileInputStream(file));
    return list;
  }

  public static List<SitemapUrl> readSourceFile(InputStream inputStream) throws IOException {
    List<SitemapUrl> list = new ArrayList<SitemapUrl>();
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br
                 = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.trim().length()==0) continue;
        var arr = line.split(",");
        list.add(new SitemapUrl(arr[0],arr[1]));
        resultStringBuilder.append(line).append("\n");
      }
    }
    return list;
  }

  public static String generateSitemap(List<SitemapUrl> urls, String lastModify) {
    String head = """
            <?xml version="1.0" encoding="UTF-8"?>
            <urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
            """;
    String bottom = """
            </urlset>
            """;
    String body = "";
    for (SitemapUrl url : urls) {
      body += """
                <!-- %s -->
                <url>
                  <loc>%s</loc>
                  <lastmod>%s</lastmod>
                </url>
              """.formatted(url.getUrl(), url.getComment(), lastModify);
    }
    return head + body + bottom;
  }

}