package pl.edu.wampa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wampa.domain.Reward;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findAll();
    List<Reward> findAllById(Long id);

//    public void delete(int id);
//
//    String findFirstByText(String text);
}
