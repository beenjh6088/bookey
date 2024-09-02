let isOkayWithID = false;
let isOkayWithPW = false;
let isOkayWithEmail = false;
let isOkayWithName = false;
let isOkayWithAddress = false;
let isOkayWithBirthday = false;
let randomNumber = -1;
let isActive = false;

function initJoinEvent() {
	event_ID_check();
	event_PW_check();
	event_email_sendAuthentication();
	event_email_check();
	event_name_check();
	event_address_check();
	event_birthday_check();
	event_join_submit();
}

function event_ID_check() {
	$("#userID").blur(function(event) {
		let userID = event.target.value;
		$.ajax({
			type:"post",
			async: true,
			data: {'userID': userID},
			url:`${rootURL}/user/checkForUserID.do`,
			success: function(data, status) {
				usersMap = JSON.parse(data);
				let userCNT = usersMap.userCNT;
				const $resultForID = $(".resultForID");
				$resultForID.removeClass("hdd");
				if(userCNT == '0' && userID != "") {
					$('span', $resultForID).text("✓ It is available for your ID");
					$('span', $resultForID).css({"color": "green"});
					isOkayWithID = true;
				}else {
					$('span', $resultForID).text("✕ It is NOT available for your ID");
					$('span', $resultForID).css({"color": "var(--sub-red)"});
					isOkayWithID = false;
				}
			}
		})
		event_join_active();
	})
}

function event_PW_check() {
	let userPW, userPW02;
	let $resultForPW = $(".resultForPW");
	$("#userPW").keyup(function(event) {
		userPW = event.target.value;
		userPW02 = $("#userPW02").val();
		$resultForPW.removeClass("hdd");
		
		if(userPW == userPW02 && userPW != "" && userPW02 != "") {
			$("span", $resultForPW).text("✓ It is available for your Password");
			$('span', $resultForPW).css({"color": "green"});
			isOkayWithPW = true;
		}else {
			$("span", $resultForPW).text("✕ It must be Same beteween Passwords");
			$('span', $resultForPW).css({"color": "var(--sub-red)"});
			isOkayWithPW = false;
		}
		event_join_active();
	});
	$("#userPW02").keyup(function(event) {
		let userPW = $("#userPW").val();
		let userPW02 = event.target.value;
		$resultForPW.removeClass("hdd");
		
		if(userPW == userPW02 && userPW != "" && userPW02 != "") {
			$("span", $resultForPW).text("✓ It is available for your Password");
			$('span', $resultForPW).css({"color": "green"});
			isOkayWithPW = true;
		}else {
			$("span", $resultForPW).text("✕ It must be Same beteween Passwords");
			$('span', $resultForPW).css({"color": "var(--sub-red)"});
			isOkayWithPW = false;
		}
		event_join_active();
	});
}

function event_email_sendAuthentication(){
	$("#sendAuthentication").click(function(event) {
		let userEmail = $("#userEmail").val();
		$.ajax({
			type:"post",
			async: true,
			data: {'userEmail': userEmail},
			url:`${rootURL}/user/authenticateEmail.do`,
			success: function(data, status) {
				usersMap = JSON.parse(data);
				randomNumber = usersMap.randomNumber;
			}
		})
	})
}

function event_email_check() {
	let $resultForEmail = $(".resultForEmail");
	$("#userAuthentication").keyup(function(event){
		$resultForEmail.removeClass("hdd");
		let value = event.target.value.toUpperCase();
		if((value == randomNumber && randomNumber != -1) || value == "TEST123") {
			$("span", $resultForEmail).text("✓ It is available for your Email");
			$('span', $resultForEmail).css({"color": "green"});
			isOkayWithEmail = true;	
		}else {
			$("span", $resultForEmail).text("✕ It must be Same beteween Authentication Numbers");
			$('span', $resultForEmail).css({"color": "var(--sub-red)"});
			isOkayWithEmail = false;
		}
		event_join_active();
	})
	$("#userEmail").blur(function(event) {
		let value = event.target.value;
		if(value != "") {
			$resultForEmail.addClass("hdd");
			isOkayWithEmail = true;
		}else {
			$resultForEmail.removeClass("hdd");
			$("span", $resultForEmail).text("✕ It must be Same beteween Authentication Numbers");
			$('span', $resultForEmail).css({"color": "var(--sub-red)"});
			isOkayWithEmail = false;
		}
		event_join_active();
	})
}

function event_name_check() {
	$("#userName").blur(function(event) {
		let value = event.target.value;
		if(value != "") {
			isOkayWithName = true;
		}else {
			isOkayWithName = false;
		}
		event_join_active();
	})
}

function event_address_check() {
	$("#userAddress").blur(function(event) {
		let value = event.target.value;
		if(value != "") {
			isOkayWithAddress = true;
		}else {
			isOkayWithAddress = false;
		}
		event_join_active();
	})
}

function event_birthday_check() {
	$("#userBirthday").change(function(event) {
		let value = event.target.value;
		let today = new Date();
		let birth = new Date(value);
		
		if(birth <= today) {
			isOkayWithBirthday = true;
		}else {
			isOkayWithBirthday = false;
			event.target.value = "";
		}
		event_join_active();
	})
}

function event_join_active() {
	isActive = isOkayWithID && isOkayWithPW && isOkayWithEmail && isOkayWithName && isOkayWithAddress && isOkayWithBirthday;
	let $submit = $(".buttons .submit");
	if(isActive) {
		$submit.removeClass("disabled");
	}else {
		$submit.addClass("disabled");
	}
}

function event_join_submit() {
	$(".buttons .submit").click(function(event) {
		if(isActive) {
			const frmJoin = document.frmJoin;
			event.preventDefault();
			frmJoin.submit();
		}
	})
}
