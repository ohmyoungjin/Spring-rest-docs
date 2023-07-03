package com.study.springrestdocs.books;

import com.study.springrestdocs.support.docs.RestDocsTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerCustom extends RestDocsTestSupport {

    @Test
    @DisplayName("책 정보 가져오기")
    public void getABook() throws Exception {
        mockMvc.perform(
                        get("/book/{id}", 1L)
                )
                .andExpect(status().isOk())
                .andDo( //rest docs 문서서 작성 시작
                        restDocs.document( // RestDocsConfig 에서 주입 받아서 class/method name 으로  build/generated-snippets/ 밑에 경로에 조각이 생기게 된다
                                pathParameters( //path param 정보 입력
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
