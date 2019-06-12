package com.vmware.talentboost.demo.service;

import com.vmware.talentboost.demo.entity.Meme;
import com.vmware.talentboost.demo.repository.MemeRepository;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class DataImpl implements DataService {

    private MemeRepository memeRepository;

    @Autowired
    public DataImpl(MemeRepository memeRepository) {
        this.memeRepository = memeRepository;
    }

    @Override
    public List<Meme> findAll() {
        List<Meme> list = memeRepository.findAll();
        list.sort(Comparator.comparing(Meme::getId).reversed());
        return list;
    }

    @Override
    public Meme findById(int id) {
        Optional<Meme> result = memeRepository.findById(id);
        Meme meme;

        if(result.isPresent()){
            meme = result.get();
        }else{
            throw  new RuntimeException("Did not find meme id - " + id);
        }
        return meme;
    }

    @Override
    public Meme save(Meme meme) {
       return memeRepository.save(meme);
    }

    @Override
    public void deleteById(int id) {
        memeRepository.deleteById(id);
    }

    @Override
    public List<Meme> findByUrl(String url) {
       return memeRepository.findByUrl(url);
    }

    @Override
    public List<Meme> findByDatePosted(Date datePosted) {
        return memeRepository.findByDatePosted(datePosted);
    }

    @Override
    public List<Meme> findByDescriptionIgnoreCaseContaining(String description) {
        return memeRepository.findByDescriptionIgnoreCaseContaining(description);
    }
}
