package com.vala.github.api.demo;

import org.kohsuke.github.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Github {

//    attr

    public void getInfo(Map<String, GHRepository> repos) {




            for (Map.Entry<String, org.kohsuke.github.GHRepository> entry : repos.entrySet()) {
                System.out.println(entry.getKey());


                if (entry.getValue().getSize() != 0) {

                    Commits commits = new Commits(entry);
                    commits.printCommits();

                    commits.printFileLineCount();
                } else {
                    System.out.println("No tiene ningun commit");
                }

            }



    }




}
