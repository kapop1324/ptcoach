package com.pt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.pt.domain.User;
import com.pt.domain.exception.DomainException;
import com.pt.domain.exception.EmptyListException;
import com.pt.domain.exception.NotFoundException;
import com.pt.domain.res.DailyResFin;
import com.pt.model.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passencoder;


	@Autowired
	private HttpServletResponse response;

	//로그아웃
	@GetMapping("/logout")
	public ResponseEntity<HashMap> logout(@RequestParam String id) {

		HashMap result = new HashMap();

		String message = "";

		result.put("message", message);

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//로그인
	@PostMapping("/login")
	public ResponseEntity<HashMap> login(@RequestBody User user) {

		HashMap result = new HashMap();

		String message = "";

		try {

			User login = userService.login(user);

			if (login == null) {
				message = "fail";
				result.put("message", message);
			} else {
				message = "success";
				result.put("user", login);
				result.put("message", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
			result.put("message", message);
		}


		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//회원가입
	@PostMapping("/join")
	public ResponseEntity<HashMap> join(@RequestBody User user) {

		HashMap result = new HashMap();
		String message = "";

		try {

			User insert = userService.join(user);
			System.out.println(insert);
			if (insert != null) {
				message = "success";
				result.put("message", message);
			} else {
				message = "fail";
				result.put("message", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
			result.put("message", message);
		}

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//아이디 중복 체크
	@GetMapping("/idcheck")
	public ResponseEntity<HashMap> idcheck(@RequestParam String email) {

		HashMap result = new HashMap();

		String message = "";

		try {

			User idcheck = userService.finduserEmail(email);

			if (idcheck == null) {
				message = "success";
				result.put("message", message);
			} else {
				message = "fail";
				result.put("message", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
			result.put("message", message);
		}


		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//내정보
	@GetMapping("/userinfo")
	public ResponseEntity<HashMap> userinfo(@RequestParam String email) {

		HashMap result = new HashMap();
		String message = "";

		try {

			User user = userService.finduserEmail(email);

			if (user == null) {
				message = "fail";
			} else {
				message = "success";
				result.put("userinfo", user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
		}

		result.put("message", message);

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//탈퇴
	@DeleteMapping("/leave")
	public ResponseEntity<HashMap> leave(@RequestParam String email) {
		System.out.println(email);
		HashMap result = new HashMap();

		String message = "";

		try {

			long leave = userService.leave(email);

			if (leave == 0) {
				message = "fail";
				result.put("message", message);
			} else {
				message = "success";
				result.put("message", message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
			result.put("message", message);
		}

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//마이페이지 일별 운동기록
	@GetMapping("/daily")
	public ResponseEntity<HashMap> dailyResult(@RequestParam String email) {

		HashMap result = new HashMap();
		String message = "";

		try {

			List<String> day = userService.day(email);

			if (day == null) {
				message = "fail";
			} else {
				message = "success";
				result.put("myRecord", day);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
		}

		result.put("message", message);

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//마이페이지 일별 운동기록
	@GetMapping("/dailydetail")
	public ResponseEntity<HashMap> dailydetailResult(@RequestParam String email, String date) {

		HashMap result = new HashMap();
		String message = "";

		try {

			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date to = transFormat.parse(date);

			List<DailyResFin> dailyRecord = userService.daydetail(email, date);

			if (dailyRecord == null) {
				message = "fail";
			} else {
				message = "success";
				result.put("dailyRecord", dailyRecord);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
		}

		result.put("message", message);

		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}

	//user 수정
	@PutMapping("/modify")
	public ResponseEntity<HashMap> userModify(@RequestBody User user) {
		HashMap result = new HashMap();
		String message = "";

		try {


			long res = userService.update(user);

			if(res == 1){
				message = "success";
				result.put("message", message);
			}else{
				message = "fail";
				result.put("message",message);

			}


		} catch (Exception e) {
			e.printStackTrace();
			message = "error";
		}
		result.put("message", message);
		return new ResponseEntity<HashMap>(result, HttpStatus.OK);
	}




}
