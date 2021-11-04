package com.vala.github.api.demo;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class GithubConnect {

    @Autowired
    private Environment env;

//    attr

    public void getRepos() {
        try {

//            Crea conexion autorizada a GitHub y pilla una lista de los repos
            String oauth = env.getProperty("oauth");
			GitHub github = new GitHubBuilder().withOAuthToken(oauth).build();
            GHUser user = github.getUser("Open-Bootcamp");
            user.getRepositories();

            for (Map.Entry<String, org.kohsuke.github.GHRepository> entry : user.getRepositories().entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue().getName());


                if (entry.getValue().getSize() != 0) {

                    PagedIterable<GHCommit> commits = entry.getValue().listCommits();


                    Iterator<GHCommit> iterator = commits.iterator();
                    String sha = "";
                    while (iterator.hasNext()) {
                        GHCommit commit = iterator.next();


                        System.out.println("Commit: " + commit.getSHA1() + ", info: " + commit.getCommitShortInfo().getMessage());
                        sha = commit.getSHA1();
                    }

                    HashMap<String, Integer> fileCount = new HashMap<>();
                    var x = entry.getValue().getCommit(sha).getFiles();
                    Iterator<GHCommit.File> iter = x.iterator();
                    while (iter.hasNext()) {
                        GHCommit.File lol = iter.next();
                        String fileName = lol.getFileName();
                        String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
                        if (!fileCount.containsKey(fileExtension)) {
                            fileCount.put(fileExtension, 1);
                        } else {
                            fileCount.put(fileExtension, fileCount.get(fileExtension) + 1);
                        }


                        System.out.println(lol.getRawUrl());
                        URL url = lol.getRawUrl();
                        StringBuilder sb = new StringBuilder();
                        String line;

                        int count = 0;
                        InputStream in = url.openStream();
                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                            while ((line = reader.readLine()) != null) {
                                count++;
                            }
                        } finally {
                            in.close();
                        }

                        System.out.println(count);
                    }

//                    System.out.println(fileCount.get("java"));
                    fileCount.entrySet().forEach(file -> {
                        System.out.println(file.getKey() + " " + file.getValue());
                    });

                } else {
                    System.out.println("No commits yet");
                }
//



//                List<GHCommit> commit = entry.getValue().listCommits().toList();
//                System.out.println(PagedIterable<GHCommit>.);
//                Iterator<org.kohsuke.github.GHCommit> iterator = commit.iterator();
//                while (iterator.hasNext()) {
//                    System.out.println(iterator.next());
//                }
            }

        } catch (
                IOException e) {
            System.out.println("Auth error");
        }

    }




}
