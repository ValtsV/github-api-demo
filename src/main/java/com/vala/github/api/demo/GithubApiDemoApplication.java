package com.vala.github.api.demo;

import org.kohsuke.github.GHRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication
public class GithubApiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GithubApiDemoApplication.class, args);
		Github gh = context.getBean(Github.class);
		GetRepos getRepos = context.getBean(GetRepos.class);

		String login = "Open-Bootcamp";
		Map<String, GHRepository> repos = getRepos.getRepos(login);

		gh.getInfo(repos);



	}



}
