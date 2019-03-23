package pl.edu.wampa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import pl.edu.wampa.domain.Reward;
import pl.edu.wampa.domain.Users;
import pl.edu.wampa.repository.RewardRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class JourneyController {

    @Autowired
    private RewardRepository rewardRepository;

    @GetMapping("/losowanie")
    public String showJourneyPlan(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Reward> rewards = rewardRepository.findAll();
        int size = rewards.size();
        if (size>0) {
            Random rand = new Random();
            Integer n = rand.nextInt(size);

            List<Reward> rewards2 = rewardRepository.findById(2L);
            List<String> reward1 = new ArrayList<>();
            for (Reward reward: rewards2)
            reward1.add(reward.getText());

            model.addAttribute("reward", reward1);
        }

        return "losowanie";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
