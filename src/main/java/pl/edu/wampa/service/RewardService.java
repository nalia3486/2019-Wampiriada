package pl.edu.wampa.service;

import pl.edu.wampa.domain.Reward;

import java.util.List;

public interface RewardService {

    List<Reward> findAll();

    List<Reward> findAllById(Long l);

//    public void delete(int id);
//
//    String findFirstByText(String t);

}
