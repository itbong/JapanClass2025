package com.example.sjw.SJWSpring.config;

import com.example.sjw.SJWSpring.member.bean.MemberBean;
import com.example.sjw.SJWSpring.member.service.MemberService;
import com.example.sjw.SJWSpring.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

//토큰을 체크하는 인터셉터
@Component
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
            sendMsg(response, "유효한 토큰값이 아닙니다.");
            return false;
        }

        //3.token 토큰안의 값을 보고 판단을 해야한다. controller로 보낼지, back시킬지
        try {
            authToken = authToken.replace("Bearer ", "");
            Jws<Claims> claimsJws = JwtUtil.verifyToken(authToken);
            String userId = (String)claimsJws.getBody().get("userId");
            String password = (String)claimsJws.getBody().get("password");
            System.out.println("userName:" + userId + ", password: " + password);

            MemberBean pMemBean = new MemberBean();
            pMemBean.setUsername(userId);
            pMemBean.setPassword(password);
            MemberBean rtnBean = memberService.selectLoginMember(pMemBean);
            if(rtnBean != null) {
                //회원을 찾았다. ==> 세션객체에 저장해서 보내준다.
                request.getSession().setAttribute("userId", rtnBean.getId());
                return true;
            }
            sendMsg(response, "존재하지 않는 유져정보의 토큰 입니다.");

        } catch (ExpiredJwtException eje) {
            //토큰이 만료
            eje.printStackTrace();
            sendMsg(response, "만료된 토큰 입니다.");
        } catch (Exception e) {
            //기타에러 발생
            e.printStackTrace();
            sendMsg(response, "올바른 토큰이 아닙니다. ");
        }

        return false;
    }


    //response 메시지를 작성한다.
    private void sendMsg(HttpServletResponse response, String msg) {
        // ✅ 응답 인코딩과 Content-Type 설정
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        //response 에러 메시지를 작성한다.
        try {
            PrintWriter writer = response.getWriter();
            String jsonMsg = "{\n" +
                    "   \"result\": \"fail\",\n" +
                    "   \"resultMsg\": \"" + msg + "\"\n" +
                    "}";
            writer.write(jsonMsg);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


};//end class
