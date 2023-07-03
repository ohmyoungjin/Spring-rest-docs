package com.study.springrestdocs.books;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class}) //Junit 4 => 5 에 바뀜에 따라 RunWith => ExtendWith 변경
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @DisplayName("책 이름 가져오기")
    public void getABook() throws Exception {
        mockMvc.perform(
                get("/book/{id}", 1L)
                )
                .andExpect(status().isOk())
                .andDo( //rest docs 문서서 작성 시작
                        document("index", //문서 조각 디렉토리 명
                                pathParameters( //path param 정보 ㅇ비력
                                        parameterWithName("id").description("bookId")
                                ),
                                responseFields( //response field 정보 입력
                                        fieldWithPath("id").description("ID"),
                                        fieldWithPath("author").description("author"),
                                        fieldWithPath("title").description("title"),
                                        fieldWithPath("publishedAt").description("publishedAt")
                                )
                        )
                );
    }




}