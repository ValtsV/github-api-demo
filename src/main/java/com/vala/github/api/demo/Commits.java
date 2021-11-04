package com.vala.github.api.demo;

import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedIterable;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Commits {

    Map.Entry<String, org.kohsuke.github.GHRepository> entry;

    public Commits(Map.Entry<String, GHRepository> entry) {
        this.entry = entry;
    }

    public void printCommits() {
        PagedIterable<GHCommit> commits = entry.getValue().listCommits();


        for (GHCommit commit : commits) {

            try {
                System.out.println("Commit: " + commit.getSHA1() + ", info: " + commit.getCommitShortInfo().getMessage());

            } catch (IOException e) {
                System.out.println("Error getting commit short info from: " + commit);
            }
        }
    }

    public String getSha() {
        Iterator<GHCommit> iterator = entry.getValue().listCommits().iterator();
        String sha = "";
        while (iterator.hasNext()) {
            GHCommit commit = iterator.next();
            if (!iterator.hasNext()) {
                sha = commit.getSHA1();
            }
        }
        return sha;
    }

    public void printFileLineCount() {
        HashMap<String, Integer> fileCount = new HashMap<>();
        String sha = getSha();
        try {
            List<GHCommit.File> fileList = entry.getValue().getCommit(sha).getFiles();
            for (GHCommit.File file : fileList) {
                String fileName = file.getFileName();
                String fileNameSubstring = fileName.substring(fileName.lastIndexOf("/") + 1);
                String fileExtension;
                if (fileNameSubstring.contains(".")) {
                    fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
                } else {
                    fileExtension = fileNameSubstring;
                }

                if (!fileCount.containsKey(fileExtension)) {
                    fileCount.put(fileExtension, 1);
                } else {
                    fileCount.put(fileExtension, fileCount.get(fileExtension) + 1);
                }



                URL url = file.getRawUrl();


                FileLineCounter lineCounter = new FileLineCounter(url);
                int count = lineCounter.countLines();

                System.out.println(file.getFileName() + " -> tiene " + count + " lineas");
            }

        } catch (IOException e) {
            System.out.println("Error in printFileLineCount");
        }

        System.out.println("Este repo tiene estos ficheros:");
        fileCount.forEach((key, value) -> System.out.println(value + " de " + key));
    }
}
