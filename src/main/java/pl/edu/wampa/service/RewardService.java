package pl.edu.wampa.service;

import pl.edu.wampa.domain.Reward;

import java.util.List;

public interface RewardService {
    List<Reward> listById(Long id);
}
