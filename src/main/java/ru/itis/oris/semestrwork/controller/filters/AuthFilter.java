package ru.itis.oris.semestrwork.controller.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    final static Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // Логируем путь, чтобы увидеть, что именно обрабатывается фильтром
        logger.debug("Запрос к ресурсу: " + httpServletRequest.getServletPath());

        // Пропускаем запросы к статическим файлам и публичным страницам
        if (httpServletRequest.getServletPath().startsWith("/static") ||
                httpServletRequest.getServletPath().startsWith("/usercheck") ||
                httpServletRequest.getServletPath().startsWith("/login") ||
                httpServletRequest.getServletPath().startsWith("/register")) {

            logger.debug("Пропускаем запрос к статическому ресурсу или публичной странице: " + httpServletRequest.getServletPath());
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Проверяем сессию для остальных запросов
            HttpSession session = httpServletRequest.getSession(false);
            if (session != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // Если сессия отсутствует, перенаправляем на страницу входа
                logger.debug("Сессия не найдена, перенаправляем на страницу входа");
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
                return; // Прерываем выполнение цепочки фильтров
            }
        }
    }
}
