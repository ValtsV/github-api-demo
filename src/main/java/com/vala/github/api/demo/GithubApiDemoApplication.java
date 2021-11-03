package com.vala.github.api.demo;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@SpringBootApplication
public class GithubApiDemoApplication {

	@Value("${oauth}")
	private static String oauth;


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GithubApiDemoApplication.class, args);
		GithubConnect gh = context.getBean(GithubConnect.class);

		gh.getRepos();



	}



}
