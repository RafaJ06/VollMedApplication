package VollMed.RestControllers;

import VollMed.Domain.Consult.ConsultDetails;
import VollMed.Domain.Consult.ConsultRequest;
import VollMed.Domain.Consult.ScheduleAppointment;
import VollMed.Domain.Doctor.Specialization;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultDetails> consultDetailsJson;

    @Autowired
    private JacksonTester<ConsultRequest> consultRequestJson;

    @MockitoBean
    private ScheduleAppointment reserve;

    @Test
    @WithMockUser
    @DisplayName("Should return 400 HTTP status code when the parameters of the consult are null")
    void book_case1() throws Exception {
        var response = mvc.perform(post("/consult")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @WithMockUser
    @DisplayName("Should return 200 HTTP status code when all the parameters have been given")
    void book_case2() throws Exception {

        LocalDateTime date = LocalDateTime.now().plusHours(1L);
        var specialization = Specialization.ORTOPEDIA;
        var consultReservationDetails = new ConsultDetails(1L, null, 2L, date);

        when(reserve.toBookAppointment(any())).thenReturn(consultReservationDetails);

        var response = mvc.perform(post("/consult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(consultRequestJson
                        .write(new ConsultRequest(1L, 2L, date,specialization)
                        ).getJson()
                )
        ).andReturn().getResponse();

var expectedJSON = consultDetailsJson
        .write(consultReservationDetails).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}