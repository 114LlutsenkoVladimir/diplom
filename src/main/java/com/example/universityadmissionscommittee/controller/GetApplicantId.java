package com.example.universityadmissionscommittee.controller;

import jakarta.servlet.http.HttpSession;

public interface GetApplicantId {
    default Long getApplicantId(HttpSession session) {
        return (Long) session.getAttribute("id");
    }
}
