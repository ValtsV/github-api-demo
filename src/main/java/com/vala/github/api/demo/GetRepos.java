package com.vala.github.api.demo;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class GetRepos {

    @Autowired
    private Environment env;
    //attr

    public Map<String, GHRepository> getRepos(String login) {
        Map<String, GHRepository> map = null;
        try {
            String oauth = env.getProperty("oauth");
            GitHub github = new GitHubBuilder().withOAuthToken(oauth).build();
            GHUser user = github.getUser(login);
            map = user.getRepositories();
        } catch (IOException e) {
            System.out.println("Error de autenticacion");
        }
        return map;
    }

}
