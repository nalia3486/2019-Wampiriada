package pl.edu.wampa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.wampa.domain.Users;
import pl.edu.wampa.service.RewardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class JourneyController {

    @Autowired
    private RewardService rewardService;

    private final static String filePath = "src/rewards.txt";
    private final static String filePath2 = "src/wylosowane.txt";
    private final static String filePath3 = "src/logi.txt";

    @GetMapping("/losowanie")
    public String showJourneyPlan(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        return "losowanie";
    }

    @PostMapping("/losowanie")
    public String showReward(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        System.out.println("Post Mapping!");

        List<String> nagrody;
        String wylosowane="Brak nagród!";
        try {
            nagrody = listaPytan();
            if (nagrody.get(0) != null) {
            System.out.println(nagrody);
            wylosowane = random(nagrody); //zwraca to, co zostalo wylosowane
            System.out.println("Wylosowano: " + wylosowane);
        }
            model.addAttribute("rewards", wylosowane);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "losowanie";
    }

    private static String random(List<String> noweNagrody) throws IOException {
        Random generator = new Random();
        int rozmiar = noweNagrody.size();
        int number = generator.nextInt(rozmiar);
        String wylosowane = noweNagrody.get(number);
        noweNagrody.remove(number);
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i!=rozmiar-1) {
            sb.append(noweNagrody.get(i));
            i++;
            sb.append(System.lineSeparator());
        }
        Files.write(Paths.get(filePath), sb.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(Paths.get(filePath2), (wylosowane+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        LocalDateTime ldt = LocalDateTime.now();
        Files.write(Paths.get(filePath3), (ldt+ ": "+ wylosowane+ System.lineSeparator()).getBytes(),
                StandardOpenOption.APPEND);
        return wylosowane;
    }

    private static List<String> listaPytan() throws Exception {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        do {
            list.add(textLine);
            textLine = bufferedReader.readLine();
        } while (textLine != null);
        bufferedReader.close();
        return list;
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
