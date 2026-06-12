package com.example.universityadmissionscommittee.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {



    public PageController() {}

    @GetMapping("/")
    public String initial(HttpSession session) {
        session.setAttribute("user", "user");
        return usersPage();
    }

    @GetMapping("/applicants/")
    public String applicantPage(HttpSession session, HttpServletRequest request) {
        return switch ((String) session.getAttribute("user")) {
            case "admin"     -> "applicants/admin-page";
            case "committee" -> "applicants/committee-page";
            case "applicant"          -> "applicants/page-for-applicant";
            default -> {
                String referer = request.getHeader("Referer");
                yield "redirect:" + (referer != null ? referer : "/");
            }
        };

    }

    @GetMapping("/specialties/")
    public String specialtyPage(HttpSession session, HttpServletRequest request) {
        return switch ((String) session.getAttribute("user")) {
            case "admin"     -> "specialties/admin-page";
            case "committee" -> "specialties/committee-page";
            default -> {
                String referer = request.getHeader("Referer");
                yield "redirect:" + (referer != null ? referer : "/");
            }
        };
    }

    @GetMapping("/users/")
    public String usersPage() {
        return "autorization";
    }


    @GetMapping("/reports/")
    public String reportPage(HttpSession session, HttpServletRequest request) {
        return switch ((String) session.getAttribute("user")) {
            case "admin", "committee" -> "reports/report";
            default -> {
                String referer = request.getHeader("Referer");
                yield "redirect:" + (referer != null ? referer : "/");
            }
        };
    }

    @GetMapping("/appeals/")
    public String appealPage(HttpSession session) {
        return "appeal/appeal-committee-page";
    }
}
