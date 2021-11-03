package com.vala.github.api.demo;

import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class GithubConnect {

    @Autowired
    private Environment env;

//    attr

    public void getRepos() {
        try {

            String oauth = env.getProperty("oauth");
			GitHub github = new GitHubBuilder().withOAuthToken(oauth).build();
            GHUser user = github.getUser("Open-Bootcamp");
           user.getRepositories();
            for (Map.Entry<String, org.kohsuke.github.GHRepository> entry : user.getRepositories().entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue().getName());
//                GHRepository lol = github.getRepository(entry.getKey());
//                toString(lol.listCommits());
                if (entry.getValue().getSize() != 0) {

                    PagedIterable<GHCommit> commits = entry.getValue().listCommits();

                    Iterator<GHCommit> iterator = commits.iterator();

                    while (iterator.hasNext()) {
                        GHCommit commit = iterator.next();
//                        Iterator<GHCommit.File> iter = commit.getFiles().iterator();
//                        while (iterator.hasNext()) {
//                            GHCommit.File lol = iter.next();
//                            System.out.println(lol.getFileName());
//                            System.out.println(lol.getRawUrl().getFile().lines());
//                        }

//                    if (commit != null) {
                        System.out.println("Commit: " + commit.getSHA1() + ", info: " + commit.getCommitShortInfo().getMessage());

//                    } else {
//                        System.out.println("No commits yet");
//                    }
                    }

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
