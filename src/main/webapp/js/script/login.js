function initLoginEvent() {
	event_login_access();
}

function event_login_access() {
	$(".button > .loginButton").click(function(event) {
		event.preventDefault();
		const frmLogin = document.frmLogin;
		frmLogin.method = "post";
		frmLogin.action = `${rootURL}/user/loginUser.do`;
		frmLogin.submit();
	})
}