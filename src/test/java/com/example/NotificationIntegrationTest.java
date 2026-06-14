

package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
class NotificationIntegrationTest {

    @Container
    static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));

    @BeforeAll
    static void setupProperties() {
        // 1. Сначала запускаем контейнер вручную, если он еще не запущен
        // (JUnit @Container сделает это, но для System.setProperty нам нужен адрес немедленно)
        if (!kafka.isRunning()) {
            kafka.start();
        }
        
        // 2. Явно прописываем адрес в системные свойства JVM
        // Это перекроет любые значения из application.properties/yml
        System.setProperty("spring.kafka.bootstrap-servers", kafka.getBootstrapServers());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    void testManualApiNotification_ShouldSendEmail() throws Exception {
        mockMvc.perform(post("/api/notify")
                        .param("email", "test@example.com")
                        .param("operation", "CREATE"))
                .andExpect(status().isOk());

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}


