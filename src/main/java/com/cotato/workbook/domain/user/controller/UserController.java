package com.cotato.workbook.domain.user.controller;

import com.cotato.workbook.domain.user.dto.UserRequest;
import com.cotato.workbook.domain.user.dto.UserResponse;
import com.cotato.workbook.domain.user.exception.UserException;
import com.cotato.workbook.domain.user.exception.code.UserErrorCode;
import com.cotato.workbook.domain.user.exception.code.UserSuccessCode;
import com.cotato.workbook.global.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API", description = "유저 관련 API") // Swagger에서 이 컨트롤러를 "User API" 그룹으로 묶어줘요
@RestController          // REST API 컨트롤러임을 선언해요
@RequestMapping("/users") // 이 컨트롤러의 모든 API는 /users로 시작해요
public class UserController {

    @Operation(summary = "유저 단건 조회", description = "ID로 유저 정보를 조회해요.")
    @ApiResponses({
            @ApiResponse(responseCode = "USER2001", description = "조회 성공"),
            @ApiResponse(responseCode = "USER4041", description = "유저를 찾을 수 없음") // 가능한 응답 코드를 문서화해요
    })
    @GetMapping("/{id}")  // GET /users/{id} 요청을 처리해요
    public CommonResponse<UserResponse> getUser(
            @Parameter(description = "조회할 유저의 ID", example = "1") // Swagger UI에 파라미터 설명과 예시값이 표시돼요
            @PathVariable Long id) {
        // 실제로는 Service를 통해 DB에서 유저를 조회해요
        // 지금은 더미 데이터를 직접 반환할게요
        if (id == 999L) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        return CommonResponse.onSuccess(UserSuccessCode.USER_FOUND,
                new UserResponse(id, "이채영", "chaeyoung@example.com"));
    }

    @Operation(summary = "유저 생성", description = "새로운 유저를 생성해요.")
    @ApiResponse(responseCode = "USER2011", description = "생성 성공")
    @PostMapping           // POST /users 요청을 처리해요
    public CommonResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        // @RequestBody: HTTP 요청 body의 JSON을 UserRequest 객체로 변환해줘요
        // 실제로는 Service를 통해 DB에 저장해요
        return CommonResponse.onSuccess(UserSuccessCode.USER_CREATED,
                new UserResponse(1L, request.getName(), request.getEmail()));
    }
}