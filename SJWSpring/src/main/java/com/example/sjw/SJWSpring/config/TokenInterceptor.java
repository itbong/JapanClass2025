package com.example.sjw.SJWSpring.config;

import com.example.sjw.SJWSpring.member.service.MemberService;
import com.example.sjw.SJWSpring.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

//토큰을 체크하는 인터셉터
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception
    {

        //1.request 에서 token 값을 꺼낸다.
        String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);


        //2.token 복호화 ==> 토큰안의 실려있는 값들을 참조할 수 있다.
        if( authToken == null ) {
            // ✅ 응답 인코딩과 Content-Type 설정
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");
            //response 에러 메시지를 작성한다.
            PrintWriter writer = response.getWriter();
            String jsonMsg = "{\n" +
                    "   \"result\": \"fail\",\n" +
                    "   \"resultMsg\": \"유효한 토큰값이 아닙니다.\"\n" +
                    "}";
            writer.write(jsonMsg);
            writer.flush();
            writer.close();
            return false;
        }

        //3.token 토큰안의 값을 보고 판단을 해야한다. controller로 보낼지, back시킬지
        try {
            authToken = authToken.replace("Bearer ", "");
            Jws<Claims> claimsJws = JwtUtil.verifyToken(authToken);
            String userId = (String)claimsJws.getBody().get("userId");
            String password = (String)claimsJws.getBody().get("password");
            System.out.println("userId:" + userId + ", password: " + password);
        } catch (ExpiredJwtException eje) {
            //토큰이 만료
            eje.printStackTrace();
        } catch (Exception e) {
            //기타에러 발생
            e.printStackTrace();
        }

        return true;
    }


    //response 메시지를 작성한다.
    private void sendMsg(HttpServletResponse response, String msg) {
        
    }


};//end class
