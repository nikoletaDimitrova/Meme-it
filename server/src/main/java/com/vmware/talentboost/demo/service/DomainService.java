package com.vmware.talentboost.demo.service;

import com.vmware.talentboost.demo.entity.Domain;
import com.vmware.talentboost.demo.entity.Meme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DomainService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    private RestTemplate restTemplate = new RestTemplate();
    private String ipDomain = "https://meme-it-platform-service-api.herokuapp.com/domain";
    private String myAddress= "http://172.26.130.193:8080";
    private String name = "MemeZone";
    private String id;
    private Meme[] memes;
    private Domain myDomain;

//    @PostConstruct
    public String register(){
          myDomain = new Domain(name, myAddress);
        try {
            id = restTemplate.postForObject(ipDomain + "/register?name="+ name + "&address=" + myAddress, myDomain, String.class);
            myDomain.setId(id);
            return id;
        }catch (Exception e){
            System.out.println("Could not Register");
            e.printStackTrace();
        }
        return null;
    }

    public Domain[] getDomains(){
       return restTemplate.getForObject(ipDomain,Domain[].class);
    }

    public Meme[] getMemes(String domain){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Meme[]> entity = new HttpEntity<>(headers);
        String address;
        for(Domain currentDomain : getDomains()){
            if(currentDomain.getAddress().equals(domain)){
                address = currentDomain.getAddress();
                memes = restTemplate.exchange(address + "/meme", HttpMethod.GET, entity, Meme[].class).getBody();
              return  memes;
            }
        }
        return null;
    }

    public List<Meme> getFilteredMemes(String filter) {
        List<Meme> filteredMemes = new ArrayList<>();
        for(Meme meme : memes)
        {
            if(meme.getDescription().toLowerCase().equals(filter.toLowerCase()))
            {
                filteredMemes.add(meme);
            }
        }

        if(filteredMemes.size() == 0)
        {
            filteredMemes.add(findClosestDescription(filter));
        }

        return filteredMemes;
    }

    private Meme findClosestDescription(String searchedMeme) {
        int minLevenStheinDistance = 200300400;
        int currentDifference;
        Meme tmp = new Meme();

        for (Meme meme : memes) {
            currentDifference = levenshteinAlgorithm(meme.getDescription(), searchedMeme);
            if (currentDifference < minLevenStheinDistance) {
                minLevenStheinDistance = currentDifference;
                tmp = meme;
            }
        }
        return tmp;
    }

        private int levenshteinAlgorithm(String description,String filter) {
        int[][] dp = new int[description.length() + 1][filter.length() + 1];

        for(int i = 0; i <= filter.length();i++){
            dp[0][i] = i;
        }
        for(int i = 0; i <= description.length();i++){
            dp[i][0] = i;
        }
        for(int i = 1; i <= description.length();i++){
            for(int j = 1; j <= filter.length();j++){
                dp[i][j] = Math.min(dp[i - 1][j - 1] + areEqual(description.charAt(i - 1), filter.charAt(j - 1)),Math.min(dp[i - 1][j] + 1,dp[i][j - 1] + 1));
            }
        }

        return dp[description.length()][filter.length()];
    }

    private int areEqual(char a, char b) {
        if(a == b)return 0;
        return 1;
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        log.info(id + " invoked");
        restTemplate.delete(ipDomain + "/deregister/" + myDomain.getId());
    }

}
