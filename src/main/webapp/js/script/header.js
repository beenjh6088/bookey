const searchArea = document.querySelector("header .searchArea");

function initHeaderEvent() {
	event_mainMenu_upDown();
	event_search_showArea();
	event_search_hideArea();
	event_href_direct();
	event_logout_disconnect();
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

function event_href_direct() {
	$("bky-rounded-button.login").click(function() {
		location.href = `${rootURL}/jsp/user/login.jsp`;
	})
	$("bky-rounded-button.join").click(function() {
		location.href = `${rootURL}/jsp/user/join.jsp`;
	})
	
	// setting links for all menus
	$(".mainMenu > li > a").each(function(idx, obj) {
		let mainMenu = obj.textContent.toLowerCase();
		$(".subMenu a", this.parentNode).each(function(index, object) {
			let rawSubMenu = object.textContent.toLowerCase();
			let arrSubMenu = rawSubMenu.split(" ");
			let subMenu = "";
			for(let i = 0; i < arrSubMenu.length; i++) {
				if(i == 0) {
					subMenu = arrSubMenu[i];
				}else {
					subMenu = subMenu + toCapitalCase(arrSubMenu[i]);
				}
			}
			let fileName = `${mainMenu}${toCapitalCase(subMenu)}.jsp`;
			let destPath = `${rootURL}/jsp/${mainMenu}/${subMenu}/${fileName}`
			$(this).attr({"href": destPath})
		})
	})
}

function event_logout_disconnect() {
	$("bky-rounded-button.logout").click(function() {
		location.href = `${rootURL}/user/logoutUser.do`;	
	})
}