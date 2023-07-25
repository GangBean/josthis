package com.gangbean.josthis.acceptance;

import com.gangbean.josthis.repository.StockRepository;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@Profile("test")
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockListAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    StockRepository stockRepository;

    private RequestSpecification spec;

    @BeforeEach
    void setup(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .setBaseUri("http://localhost")
                .setPort(port)
                .build();
    }

    /**
     * given: 데이터베이스에 주식정보가 20개 넘게 존재하고
     * and : url이 존재할 때
     * when: 주식목록 조회 요청을 하면
     * then: 등록된 주식목록이 컨센서스스코어가 낮은 순서대로 20개만 조회됩니다.
     */
    @DisplayName("주식목록조회_기본순서_컨센서스스코어_상위_20개")
    @Test
    void getStockListDefaultOrder() {
        // given
        assertThat(stockRepository.findAll()).hasSizeGreaterThan(20);

        // and
        final String url = "/api/stocks";

        // when
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .spec(spec)
                .contentType(ContentType.JSON)
                .filter(document("stocks",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("stocks").description("주식 정보"),
                                fieldWithPath("stocks[].id").description("주식 ID"),
                                fieldWithPath("stocks[].name").description("주식명"),
                                fieldWithPath("stocks[].tickerCode").description("주식 코드"),
                                fieldWithPath("stocks[].price").description("주식 가격"),
                                fieldWithPath("stocks[].consensusScore").description("주식 컨센서스 점수"),
                                fieldWithPath("stocks[].consensusType").description("주식 컨센서스 종류")
                        )))
                .when()
                .get(url)
                .then().log().all()
                .extract();

        // then
        List<BigDecimal> consensusScoreList = response.jsonPath().getList("stocks.consensusScore", BigDecimal.class);
        assertAll(
                () -> assertThat(response.jsonPath().getList("stocks.id", Long.class)).hasSize(20),
                () -> assertThat(consensusScoreList).isEqualTo(consensusScoreList.stream().sorted().collect(Collectors.toList()))
        );
    }
}
