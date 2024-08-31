const searchArea = document.querySelector("header .searchArea");

function initHeaderEvent() {
	event_mainMenu_upDown();
	event_search_showArea();
	event_search_hideArea();
	event_bkyRoundedButton_direct();
}

function event_mainMenu_upDown() {
	$(".mainMenu > li").mouseover(function() {
    $(".subMenu").show();
    $(".navibg").css({
      'height':'200px'
    });
	}).mouseout(function() {
    $(".subMenu").hide();
    $(".navibg").css({
      'height':'0'
    });
	})
}

function event_search_showArea() {
	// open searchArea
	$("header .search > .icon").click(function() {
		let isShow = !(searchArea.classList.contains("hdd"));
		if(isShow == false) {
			searchArea.classList.remove("hdd");
		}
	})
}

function event_search_hideArea() {
	// close searchArea
	searchArea.addEventListener("click" , function(event) {
		const container = $(".container", searchArea)[0];
		if(!container.contains(event.target)){
			searchArea.classList.add("hdd");
		}
	})
}

function event_bkyRoundedButton_direct() {
	$("bky-rounded-button.login").click(function() {
//		console.log(`${rootURL}`);
		location.href = `${rootURL}/jsp/user/login.jsp`;
	})
	$("bky-rounded-button.join").click(function() {
//		console.log(22);
		location.href = `${rootURL}/jsp/user/join.jsp`;
	})
}