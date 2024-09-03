function initAsideEvent() {
	event_menu_direct();
}

function event_menu_direct() {
	$("#aside").each(function(idx, obj) {
		let mainMenu = $(".mainMenu span", obj).text().toLowerCase();
		$(".subMenu > li > a", obj).each(function(index, object){
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
		});
	})
}