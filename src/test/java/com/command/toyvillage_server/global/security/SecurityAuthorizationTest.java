package com.command.toyvillage_server.global.security;

import com.command.toyvillage_server.domain.app.auth.account.service.EmployeeCreateService;
import com.command.toyvillage_server.domain.web.news.service.NewsCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeCreateService employeeCreateService;

    @MockitoBean
    private NewsCreateService newsCreateService;

    @Test
    void 앱_관리자만_직원_계정을_생성할_수_있다() throws Exception {
        mockMvc.perform(post("/app/admin/employees")
                        .with(user("app-admin").roles("APP_ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "employee01",
                                  "name": "직원"
                                }
                                """))
                .andExpect(status().isCreated());

        verify(employeeCreateService).execute(any());
    }

    @Test
    void 웹_관리자는_직원_계정을_생성할_수_없다() throws Exception {
        mockMvc.perform(post("/app/admin/employees")
                        .with(user("web-admin").roles("WEB_ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "employee01",
                                  "name": "직원"
                                }
                                """))
                .andExpect(status().isForbidden());

        verifyNoInteractions(employeeCreateService);
    }

    @Test
    void 직원은_직원_계정을_생성할_수_없다() throws Exception {
        mockMvc.perform(post("/app/admin/employees")
                        .with(user("employee").roles("EMPLOYEE"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "employee02",
                                  "name": "직원"
                                }
                                """))
                .andExpect(status().isForbidden());

        verifyNoInteractions(employeeCreateService);
    }

    @Test
    void 앱_관리자는_웹_콘텐츠를_작성할_수_없다() throws Exception {
        mockMvc.perform(post("/news")
                        .with(user("app-admin").roles("APP_ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "news_title": "제목",
                                  "news_description": "내용",
                                  "file_keys": []
                                }
                                """))
                .andExpect(status().isForbidden());

        verifyNoInteractions(newsCreateService);
    }

    @Test
    void 웹_관리자는_앱_계정_비밀번호를_변경할_수_없다() throws Exception {
        mockMvc.perform(patch("/app/auth/password")
                        .with(user("web-admin").roles("WEB_ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "currentPassword": "old-password",
                                  "newPassword": "new-password"
                                }
                                """))
                .andExpect(status().isForbidden());
    }
}
