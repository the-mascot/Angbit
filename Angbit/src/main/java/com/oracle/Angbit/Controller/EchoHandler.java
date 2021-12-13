package com.oracle.Angbit.Controller;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler {

	// 로그인중인 개별 유저
	Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();
	
	// 클라이언트가 서버로 연결시
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String senderID = getMemberId(session);	// 접속한 유저의 http 세션을 조회하여 id를 얻는 함수
		log("접속 성공");
		if(senderID != null) {	// 로그인 값이 있는 경우만
			log(senderID+" 연결됨");
			users.put(senderID, session);	// 로그인중 개별유저 저장
		}
	}
	
	// 클라이언트가 Data 전송 시
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log(payload);
		// 특정 유저에게 보내기
//		WebSocketSession targetSession = users.get("dmstn1812@naver.com");	// 메세지 받을 세션 조회
//		// 실시간 접속시
//		if(targetSession != null) {
//		// ex: [&분의일] 신청이 들어왔습니다.
//		}
		TextMessage tmpMsg = new TextMessage("안녕 디지몬");
		session.sendMessage(tmpMsg);
			
	}
	
	// 연결 해제될 때
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String senderID = getMemberId(session);
		if(senderID != null) {	// 로그인 값이 있는 경우만
			log(senderID+" 연결 종료됨");
			users.remove(senderID);
		}
	}
	
	// 에러 발생시
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log(session.getId()+" Exception 발생 : " + exception.getMessage());
	}
	
	// 로그 메세지
	private void log(String logmsg) {
		System.out.println(new Date() + " : " + logmsg);
	}
	
	// 웹소켓에 id 가져오기
	// 접속한 유저의 http 세션을 조회하여 id를 얻는 함수
	public String getMemberId(WebSocketSession session) {
		Map<String, Object> httpSession = session.getAttributes();
		String sessionID = (String) httpSession.get("sessionID");	// 세션에 저장된 sessionID 기준 조회
		
		return sessionID == null? null : sessionID;
	}
	
}
