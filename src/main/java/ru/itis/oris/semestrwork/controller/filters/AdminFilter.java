package ru.itis.oris.semestrwork.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.oris.semestrwork.model.User;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AdminFilter implements Filter {
    private static final List<String> restrictedPages = Arrays.asList(
            "/create_event.ftl", "/create_event", "/check_complaints", "/complaint",
            "/rooms", "/users", "/user", "/edit_user", "/update_user"
    );

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession(false);
        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        // Если пользователь не администратор и пытается попасть на ограниченную страницу
        if (restrictedPages.contains(requestURI) && (user == null || !user.isAdministratorRights())) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/home");
            return; // Прерываем выполнение цепочки фильтров
        }

        chain.doFilter(request, response); // Продолжаем выполнение цепочки фильтров
    }
}
