package com.study.springrestdocs.config;


import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import static org.springframework.restdocs.snippet.Attributes.Attribute;

/**
 * 테스트 코드 리팩토링
 * 1. 테스트 코드에서 andDo(document()) 부분에서 문서명을 항상 지정해줘야 하는 점
 * 2. 테스트 코드로 인해 build/generated-snippets에 생성된 파일 내용들을 보면 json 포멧이 한줄로 작성되어 보기 매우 불편한 점
 * 3. 관례상 andDo(print()) 를 모두 붙이는데 이 코드가 중복된다는 점
 */
@TestConfiguration
public class RestDocsConfig {

    @Bean
    public RestDocumentationResultHandler write() {
        return MockMvcRestDocumentation.document(
                "{class-name}/{method-name}", //1번 문제 해결 코드 => 조각이 생성되는 디렉토리 명을 클래스명/ 메서드 명으로 정합니다.
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()), //2번 문제 해결 코드 => json이 한 줄로 출력되던 내용을 pretty 하게 만들어준다.
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        );
    }

    public static final Attribute field( // rest docs에서 기본적으로 문서작성에 필요한 optional(필수값여부),
                                         // description(설명) 같은 체이닝 메서드는 제공하지만 제약조건 같이 커스텀으로 작성하는 내용에 대한 기능은 없습니다.
                                         //따라서 Attribute를 이용해 key, value 값으로 넣어주기 위한 함수입니다.
            final String key,
            final String value) {
        return new Attribute(key, value);
    }

}
