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
public class LosowanieController {

    private final static String filePath = "src/rewards.txt";
    private final static String filePath2 = "src/wylosowane.txt";

    @GetMapping("/wylosowane")
    public String showReward(Model model, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<String> nagrody;
        List<String> wylosowane;
        try {
            nagrody = listaPytan(filePath);
            wylosowane = listaPytan(filePath2);
            if (nagrody.get(0) != null) {
            System.out.println(nagrody);
            System.out.println(wylosowane);
        }
            model.addAttribute("nagrody", nagrody);
            model.addAttribute("wylosowane", wylosowane);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "wylosowane";
    }
    private static List<String> listaPytan(String path) throws Exception {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String textLine = bufferedReader.readLine();
        do {
            list.add(textLine);
            textLine = bufferedReader.readLine();
        } while (textLine != null);
        bufferedReader.close();
        return list;
    }
}
