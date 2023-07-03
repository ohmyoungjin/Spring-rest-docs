package com.study.springrestdocs.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoNumberGeneratorTest {

    final LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator(); //테스트할 class

    @DisplayName("로또 번호 갯수 테스트")
    @Test
    void lottoNumberSizeTest() {
        // given

        final int price = 1000;

        // when
        final List<Integer> lottoNumber = lottoNumberGenerator.generate(price);

        // then
        assertThat(lottoNumber.size()).isEqualTo(6);
    }

    @DisplayName("로또 번호 범위 테스트")
    @Test
    void lottoNumberRangerTest() {
        // given
        final int price = 1000;

        // when
        final List<Integer> lotto = lottoNumberGenerator.generate(price);

        // then
        assertThat(lotto.stream().allMatch(v -> v >= 1 && v <= 45)).isTrue(); // 1보다 같거나 크고, 45보다 작거나 같은지

    }

    @DisplayName("로또 가격 테스트")
    @Test
    void lottoPriceTest() {
        // given
        final int price = 2000;

        // when
        final RuntimeException exception = assertThrows(RuntimeException.class, () -> lottoNumberGenerator.generate(price));

        // then
        assertThat(exception.getMessage()).isEqualTo("올바른 금액이 아닙니다.");

    }
}
