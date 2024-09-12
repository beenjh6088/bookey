const searchArea = document.querySelector("#header .searchArea");

function initHeaderEvent() {
	event_mainMenu_upDown();
	event_search_showArea();
	event_search_hideArea();
	event_href_direct();
	event_logout_disconnect();
}

function event_mainMenu_upDown() {
	$("#header .mainMenu.wide > li").mouseover(function() {
    $("header .mainMenu.wide .subMenu").show();
    $("header .navibg.wide").css({
      'height':'200px'
    });
	}).mouseout(function() {
    $("header .mainMenu.wide .subMenu").hide();
    $("header .navibg.wide").css({
      'height':'0'
    });
	})
	
	$("#menu .menu").click(function() {
		$("#menu .accordion").stop().slideToggle();
	})
	
	const acc = document.getElementsByClassName("accordion-btn");
	let i;
	// Add event listener to each button
	for (i = 0; i < acc.length; i++) {
	    acc[i].addEventListener("click", function() {
	        // Toggle between hiding and showing the panel
	        this.classList.toggle("active");
	
	        let subMenu = this.nextElementSibling;
	        if (subMenu.style.display === "block") {
	            subMenu.style.display = "none";
	        } else {
	            subMenu.style.display = "block";
	        }
	    });
	}
}

function event_search_showArea() {
	// open searchArea
	$("#header .search > .icon").click(function() {
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
	$("#header bky-rounded-button.login").click(function() {
		let redirectPage = fullPath;
		location.href = `${rootURL}/jsp/user/login.jsp?redirectPage=${redirectPage}`;
	})
	$("#header bky-rounded-button.join").click(function() {
		location.href = `${rootURL}/jsp/user/join.jsp`;
	})
	$("#header bky-rounded-button.checkout").click(function() {
		location.href = `${rootURL}/jsp/user/checkout.jsp`;
	})
	$("#header bky-rounded-button.reservation").click(function() {
		location.href = `${rootURL}/jsp/user/reservation.jsp`;
	})
	
	// setting links for all menus
	$("#header .bottom .mainMenu > li > a").each(function(idx, obj) {
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
	$("#header bky-rounded-button.logout").click(function() {
		location.href = `${rootURL}/user/logoutUser.do`;	
	})
}
