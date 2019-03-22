package pl.edu.wampa.service;

import pl.edu.wampa.domain.Reward;

import java.util.Optional;

public interface RewardService {
    Optional<Reward> listById(Long id);
}
