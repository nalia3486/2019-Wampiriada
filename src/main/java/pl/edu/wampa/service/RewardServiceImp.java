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

    public List<Reward> listById(Long id) {
        return rewardRepository.findById(id);
    }
}
