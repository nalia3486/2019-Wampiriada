package pl.edu.wampa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wampa.domain.Reward;
import pl.edu.wampa.repository.RewardRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class RewardServiceImp implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public List<Reward> findAll() {
        return rewardRepository.findAll();
    }

    @Override
    public List<Reward> findAllById(Long l) {
        return rewardRepository.findAllById(l);
    }
}
