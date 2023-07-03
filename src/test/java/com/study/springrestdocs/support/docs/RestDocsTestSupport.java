package com.study.springrestdocs.support.docs;

import com.study.springrestdocs.config.RestDocsConfig;
import com.study.springrestdocs.support.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * @ExtendWith(RestDocumentationExtension.class)
 * 앞선 코드에서는 @AutoConfigureRestDocs로 자동으로 주입시켰지만, 이제 중복 작업을 제거하기 위해서는 직접 MockMvc를 커스텀해서 주입해야합니다.
 * 따라서 자동 주입이 아니라 필요한 것들을 가져와서 주입하기 위해 사용하는 코드입니다.
 * @Import(RestDocsConfig.class)
 * 앞서 작성한 Config를 추가해주는 코드입니다.
 */
@Disabled
@Import(RestDocsConfig.class)
@ExtendWith(RestDocumentationExtension.class)
public class RestDocsTestSupport extends ControllerTest {

    @Autowired
    protected RestDocumentationResultHandler restDocs; //RestDocsConfig 에서 custom 한 bean 을 주입 받아서 사용한다.

    @BeforeEach
    void setUp(final WebApplicationContext context,
               final RestDocumentationContextProvider provider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(provider)) // rest docs 설정 주입
                .alwaysDo(MockMvcResultHandlers.print()) // andDo(print()) 코드 포함 -> 3번 문제 해결
                .alwaysDo(restDocs) // pretty 패턴과 문서 디렉토리 명 정해준것 적용
                .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글 깨짐 방지
                .build();
    }
}
