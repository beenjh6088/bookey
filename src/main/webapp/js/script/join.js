let isOkayWithID = false;
let isOkayWithPW = false;
let isOkayWithEmail = false;

function initJoinEvent() {
	event_ID_check();
	event_PW_check();
	event_email_authenticate();
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
				if(userCNT == '0') {
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
	})
}

function event_PW_check() {
	$("#userPW02").keyup(function(event) {
		let userPW = $("#userPW").val();
		let userPW02 = event.target.value;
		let $resultForPW = $(".resultForPW");
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
	});
}

function event_email_authenticate(){
	$("#sendAuthentication").click(function(event) {
		let userEmail = $("#userEmail").val();
		$.ajax({
			type:"post",
			async: true,
			data: {'userEmail': userEmail},
			url:`${rootURL}/user/authenticateEmail.do`,
			success: function(data, status) {
				usersMap = JSON.parse(data);
				let randomNumber = usersMap.randomNumber;
				console.log(randomNumber);
			}
		})
	})
}